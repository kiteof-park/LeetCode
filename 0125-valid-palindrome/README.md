<h2><a href="https://leetcode.com/problems/valid-palindrome">125. Valid Palindrome</a></h2><h3>Easy</h3><hr><p>A phrase is a <strong>palindrome</strong> if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.</p>

<p>Given a string <code>s</code>, return <code>true</code><em> if it is a <strong>palindrome</strong>, or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;A man, a plan, a canal: Panama&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> &quot;amanaplanacanalpanama&quot; is a palindrome.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;race a car&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> &quot;raceacar&quot; is not a palindrome.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot; &quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> s is an empty string &quot;&quot; after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>s</code> consists only of printable ASCII characters.</li>
</ul>

---
## 팰린드롬(Palindrome)
💡`Palindrome` : 앞뒤가 똑같은 단어나 문장, 뒤집어도 같은 말이 되는 단어 또는 문장
- 대소문자 구분❌
- 영문자와 숫자만을 대상

### 감격스러운 나의 첫 코테 ..! 

### 해결방안
- [x] 문자열 전처리를 위해 `trim()`, `toLowerCase()`
- [x] 불순문자를 제거하고 영문자와 숫자만 문자열로 저장할 수 있는 방법을 생각하다가  
      `append()` 메서드를 사용할 수 있는 `StringBuilder`를 생성
- [x] 문자열을 문자 배열로 변환하고, for문을 돌면서 영문자와 숫자인 경우에만 `sb`에 추가
- [x] 모두 추가된 `sb`로 팰린드롬을 판별  

### 고민했던 점
💭 팰린드롬은 문자열이 대칭을 이루기 때문에 판별을 어떻게 해야될지 고민할 때  
굳이 문자열의 길이만큼(`length()`) 반복을 돌아야할까,  
양쪽 문자열이 대칭인 경우(일치하는 경우) 이 횟수를 저장하는 변수를 둬야할까 복잡하게 생각했는데  
우선 `charAt()`을 사용하는 `if`문을 작성하고 보니 이후엔 수월하게 풀었다 !   
(엄마 아부지 언니들 다 코테 풀었어)
```java
        for(int i = 0; i < len1; i++){
            if(sb.charAt(i) == sb.charAt(len2)){
                len2--;
                continue;
            } else{
                return false;
            }
        }
    
        return true;
```

<img src = "https://github.com/user-attachments/assets/74ee22cb-ff96-48cb-9f0a-11aa62ee73ed">
