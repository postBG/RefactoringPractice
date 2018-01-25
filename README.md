# RefactoringPractice
Practice refactoring using many legacy codes

~~나는 누구, 여긴 어디~~, ~~회사~~

### Usage

각 하위 프로젝트들은 maven import해서 보면 됨

original은 legacy 코드인데 이것을 리펙토링하는 것이 목적



### Memo

#### Code Smell

* 누군가 필요없는 Abstraction이라고 우긴다면 근거로 code smell을 들이대자
* Feature Envy
  * High Cohesion & Low Coupling
  * Tell Don't Ask: 그냥 시키면 되는데 물어보고 자기가 하면 노답 ㅉㅉ
  * 이런 경우 참조하는 클래스로 메소드를 move할 수 있는지 고려해봐야함

#### Other things

* 좋은 프로그래머? 
  * 요구사항에 맞게 설계를 변경할 수 있는가?
* 전문 용어
  * Guard clause
  * Type Code (Type마다 분기를 일으키는 코드, 예를 들면 switch)
    * 프로그램은 타입이 추가되는 방향으로 요구사항이 바뀜
    * 따라서 switch 문은 딱 1번만 허용됨. 여러번이면 바로 리팩토링해야함
    * OCP