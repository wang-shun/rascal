@license{
  Copyright (c) 2009-2013 CWI
  All rights reserved. This program and the accompanying materials
  are made available under the terms of the Eclipse Public License v1.0
  which accompanies this distribution, and is available at
  http://www.eclipse.org/legal/epl-v10.html
}
module vis::KeySym

data KeySym =
  keyPrintable(str key)
| keyAltLeft()
| keyAltRight()
| keyArrowDown()
| keyArrowLeft()
| keyArrowRight()
| keyArrowUp()
| keyBackSpace()
| keyBreak()
| keyCapsLock()
| keyCommandLeft()
| keyCommandRight()
| keyControlLeft()
| keyControlRight()
| keyEnd()
| keyEnter()
| keyEscape()
| keyF1()
| keyF10()
| keyF11()
| keyF12()
| keyF13()
| keyF14()
| keyF15()
| keyF16()
| keyF17()
| keyF18()
| keyF19()
| keyF2()
| keyF20()
| keyF3()
| keyF4()
| keyF5()
| keyF6()
| keyF7()
| keyF8()
| keyF9()
| keyHelp()
| keyHome()
| keyInsert()
| keyKeypad0()
| keyKeypad1()
| keyKeypad2()
| keyKeypad3()
| keyKeypad4()
| keyKeypad5()
| keyKeypad6()
| keyKeypad7()
| keyKeypad8()
| keyKeypad9()
| keyKeypadAdd()
| keyKeypadCr()
| keyKeypadDecimal()
| keyKeypadDivide()
| keyKeypadEqual()
| keyKeypadMultiply()
| keyKeypadSubtract()
| keyNumLock()
| keyPageDown()
| keyPageUp()
| keyPause()
| keyPrintScreen()
| keyScrollLock()
| keyShiftLeft()
| keyShiftRight()
| keyTab()
| keyUnknown(int keyCode);


data KeyModifier =
	  modCtrl()
	| modAlt()
	| modShift()
	| modCommand();
	
