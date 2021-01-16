Обязательно нужен был один общий контроллер, так как система должна отслеживать все 6 датчиков.

Дано:
1. Ультразвуковой датчик расстояния HC-SR04 - 6 штук + резисторы для понижения тока для Малинки 
(см схему подключения http://wikihandbk.com/ruwiki/images/f/f9/Rasp2_hc-sr04.png)
2. Вертикальная светодиодная лента х2 - 6 штук
3. Реле замыкания/размыкания тока для светодиодных лент х2 - 6 штук
4. Питание, соединительные провода, термоусадка и т.д.
5. Rasberry pi 4B+

Алгоритм:
Когда в зону 1-го или 6-го датчика входит человек, то программа блокирует другие датчики и
 отслеживает только крайний к активному. Т.е. движения света повторяют движение человека.
 
Пример: зашли слева, сработал первый датчик и отключился. Второй ожидает изменение, остальные не реагируют на изменения.
Делаем шаг, второй датчик сработал, первый блок отключился. Первый и третий датчики в ожидании сигнала.
Делаем шаг, третий датчик сработал, второй блок отключился. второй и четвертый датчики в ожидании сигнала.
Делаем шаг назад, сработал второй датчик, загорелся второй блок, третий отключился.
Это работает даже если мы стоим двумя ногами, то есть смотрим по последнему изменению состояния.
 Если вдруг кто-то преднамеренно станет перепрыгивать, то цикл начинается заново. То есть, после
  состояния полного покоя, когда ни в одном из датчиков нет объекта и он появляется, например, сразу на втором,
то всё происходит по вышеописанному алгоритму.