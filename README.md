# TouchEventDispatchDemo
点击事件分发
### 基本事件分发为：
```
Activity.dispatchTouchEvent()->PhoneWindow.superDispatchEvent()->Decor.superDispatchEvent()->Frame.dispatchTouchEvent()->viewGroup.dispatchTouchEvent()->view.dispatchTouchEvent
```
```
ViewGroup.dispatchTouchEvent()->viewGroup.interceptTouchEvent()->View.dispatchTouchEvent()->view.onTouch()->view.onTouchEvent()->view.performClick()->view.onClick()->view.didspatchTouchEvent()->ViewGroup.onTouchEvent()->ViewGroup.dispatchTouchEvent()->Activity.dispatchTouchEvent()->Activity.onTouchEvent()
```
### 源码解析
#### Activity
Activity.dispatchTouchEvent()
```
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            onUserInteraction();
        }
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }
```
* 入口是Activity.dispatchTouchEvent().
* Action==Down的时候，调用onUserInteraction(),可以重写该方法做一些处理。
* getWindow获取到的是PhoneWindow
PhoneWindow.superDispatchTouchEvent()
```
public boolean superDispatchTouchEvent(MotionEvent event) {
    return mDecor.superDispatchTouchEvent(event);
}
```
mDecor是DecorView
DecorView.superDispatchTouchEvent
```
    public boolean superDispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }
```
DecorView继承自FrameLayout,FrameLayout继承自ViewGroup，所以这里调用的是ViewGroup.dispatchTouchEvent()
ViewGroup.dispatchTouchEvent()
```
public dispatchTouchEvent() {

    ...

    final boolean intercepted;
    if (actionMasked == MotionEvent.ACTION_DOWN || mFirstTouchTarget != null) {
        final boolean disallowIntercept = (mGroupFlags & FLAG_DISALLOW_INTERCEPT) != 0;
        if (!disallowIntercept) {
            intercepted = onInterceptTouchEvent(ev);// A
            ev.setAction(action); // restore action in case it was changed
        } else {
            intercepted = false;
        }
    } else {
        // There are no touch targets and this action is not an initial down
        // so this view group continues to intercept touches.
        intercepted = true;
    }

    ...

    for (int i = childrenCount - 1; i >= 0; i--) {
        final int childIndex = getAndVerifyPreorderedIndex(childrenCount, i, customOrder);
         final View child = getAndVerifyPreorderedView(preorderedList, children, childIndex);
         if (!child.canReceivePointerEvents()|| !isTransformedTouchPointInView(x, y, child, null)) {// B
            continue;
         }
    }

    ...

    if (dispatchTransformedTouchEvent(ev, false, child, idBitsToAssign)) {// C
        // Child wants to receive touch within its bounds.
        mLastTouchDownTime = ev.getDownTime();
        if (preorderedList != null) {
            // childIndex points into presorted list, find original index
            for (int j = 0; j < childrenCount; j++) {
                if (children[childIndex] == mChildren[j]) {
                    mLastTouchDownIndex = j;
                    break;
                }
            }
        } else {
            mLastTouchDownIndex = childIndex;
        }
        mLastTouchDownX = ev.getX();
        mLastTouchDownY = ev.getY();
        newTouchTarget = addTouchTarget(child, idBitsToAssign);
        alreadyDispatchedToNewTouchTarget = true;
        break;
    }
```
A. 调用onInterceptTouchEvent，可以重写改方法来改变流程走向。
B. 循环遍历child判断是否在有效范围内
C. 进入dispatchTransformedTouchEvent
ViewGroup.dispatchTransformTouchEvent
```
        if (child == null) {
            handled = super.dispatchTouchEvent(transformedEvent);// A
        } else {
            final float offsetX = mScrollX - child.mLeft;
            final float offsetY = mScrollY - child.mTop;
            transformedEvent.offsetLocation(offsetX, offsetY);
            if (! child.hasIdentityMatrix()) {
                transformedEvent.transform(child.getInverseMatrix());
            }

            handled = child.dispatchTouchEvent(transformedEvent);// B
        }
```
这段逻辑表明，ViewGroup会不断向子孩子分发时间，如果没有了child，就会调用super.dispatchTouchEvent(),及View.dispatchTouchEvent()
View.dispatchTouchEvent
```
dispatchTouchEvent() {
            if (li != null && li.mOnTouchListener != null
                    && (mViewFlags & ENABLED_MASK) == ENABLED
                    && li.mOnTouchListener.onTouch(this, event)) {// A
                result = true;
            }

            if (!result && onTouchEvent(event)) {// B
                result = true;
            }
}
```
在View中先调用了onTouch，如果onTouch没处理，就会调用B处的onTouchEvent.