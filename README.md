# TouchEventDispatchDemo
点击事件分发
### 基本事件分发为：
```
Activity.dispatchTouchEvent()->PhoneWindow.superDispatchEvent()->Decor.superDispatchEvent()->Frame.dispatchTouchEvent()->viewGroup.dispatchTouchEvent()->view.dispatchTouchEvent
```
```
ViewGroup.dispatchTouchEvent()->viewGroup.interceptTouchEvent()->View.dispatchTouchEvent()->view.onTouch()->view.onTouchEvent()->view.didspatchTouchEvent()->ViewGroup.onTouchEvent()->ViewGroup.dispatchTouchEvent()->Activity.dispatchTouchEvent()->Activity.onTouchEvent()
```
