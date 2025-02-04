<h2><a href="https://leetcode.com/problems/reverse-string">344. Reverse String</a></h2><h3>Easy</h3><hr><p>Write a function that reverses a string. The input string is given as an array of characters <code>s</code>.</p>

<p>You must do this by modifying the input array <a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank">in-place</a> with <code>O(1)</code> extra memory.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> s = ["h","e","l","l","o"]
<strong>Output:</strong> ["o","l","l","e","h"]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> s = ["H","a","n","n","a","h"]
<strong>Output:</strong> ["h","a","n","n","a","H"]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is a <a href="https://en.wikipedia.org/wiki/ASCII#Printable_characters" target="_blank">printable ascii character</a>.</li>
</ul>

---
## 문자열 뒤집기
### 제약조건
`"You must do this by modifying the input array in-place with O(1) extra memory."`

💡 새로운 배열을 생성하지 않고, 주어진 입력 배열을 직접 수정해야 한다.  
💡 O(1) 추가 메모리만 사용해야 한다 _- 변수⭕, 배열/리스트 할당❌_

### 우당탕탕 나의 두 번째 코테 성공기
```java
        int start = 0;
        int end = s.length - 1;
         
        while(start < end){
            char tmp = s[start];
            s[start] = s[end];
            s[end] = tmp;

            start++;
            end--;
        }
```
- [x] `for`문을 사용하지 않고 `while`문을 사용하기 위해 `start`, `end` 를 정의
  - [x] `end`는 `s.length`가 아닌 `s.length-1`로 정의한다.
- [x] **주어진 입력 배열을 직접 수정해 뒤집어진 결과를 만들기 위해서는 양 끝의 요소를 교환**해야 한다.
  - [x] `temp` 변수를 이용해 두 변수의 값을 교환한다.
  - [x] 교환이 끝나면(한 번의 반복이 끝나면) `start++`, `end--`로 값을 조정한다.
  - [x] 값의 교환은(반복은) 배열`s`의 길이의 절반만 진행되어야 한다.
  
### 우여곡절
🚀 1. `for`문 쓰다가 조졌다
- `for`문 조건식을 작성할 때 `i`가 0이 아닌 s.length-1부터 역순으로 시작되도록 작성했었다.
- 그러다보니 ArrayIndexOutOfBoundsException 발생 ..! 왜 그랬지 내 자신 ?
- `for`문을 계속 사용하려면 `s.length / 2` 와 같은 식을 써야하는데,
  깔끔하게 작성하고 싶어서 `while`문을 사용

🚀 2. 반복의 횟수를 고려하지 않았다.
- 문자를 교환할 때 배열 길이만큼 반복하다보니 중복 교환이 발생
- 어쩐지 그대로더라 ..

### 결과 
![image](https://github.com/user-attachments/assets/52953e9a-55d6-4c2d-8480-8f9bfee3c074)
