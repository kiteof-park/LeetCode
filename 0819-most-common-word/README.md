<h2><a href="https://leetcode.com/problems/most-common-word">819. Most Common Word</a></h2><h3>Easy</h3><hr><p>Given a string <code>paragraph</code> and a string array of the banned words <code>banned</code>, return <em>the most frequent word that is not banned</em>. It is <strong>guaranteed</strong> there is <strong>at least one word</strong> that is not banned, and that the answer is <strong>unique</strong>.</p>

<p>The words in <code>paragraph</code> are <strong>case-insensitive</strong> and the answer should be returned in <strong>lowercase</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> paragraph = &quot;Bob hit a ball, the hit BALL flew far after it was hit.&quot;, banned = [&quot;hit&quot;]
<strong>Output:</strong> &quot;ball&quot;
<strong>Explanation:</strong> 
&quot;hit&quot; occurs 3 times, but it is a banned word.
&quot;ball&quot; occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
Note that words in the paragraph are not case sensitive,
that punctuation is ignored (even if adjacent to words, such as &quot;ball,&quot;), 
and that &quot;hit&quot; isn&#39;t the answer even though it occurs more because it is banned.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> paragraph = &quot;a.&quot;, banned = []
<strong>Output:</strong> &quot;a&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= paragraph.length &lt;= 1000</code></li>
	<li>paragraph consists of English letters, space <code>&#39; &#39;</code>, or one of the symbols: <code>&quot;!?&#39;,;.&quot;</code>.</li>
	<li><code>0 &lt;= banned.length &lt;= 100</code></li>
	<li><code>1 &lt;= banned[i].length &lt;= 10</code></li>
	<li><code>banned[i]</code> consists of only lowercase English letters.</li>
</ul>
---

## 가장 흔한 단어
- 금지된 단어를 제외하고 가장 흔하게 등장하는 단어를 출력
- 대소문자를 구분하지 않으며, 구두점(마침표, 쉼표)를 무시한다.
---
## 1트 *- 실패💔*
```java
class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        StringBuilder sb = new StringBuilder();

        for(char c : paragraph.toLowerCase().toCharArray()){
            if(Character.isLetter(c) || c == ' '){
                sb.append(c);
            }
        }

        String str = sb.toString();

        for(String ban : banned){
            str = str.replace(ban, "");
        }

        Map<String, Integer> wordsMap = new HashMap<>();
        for(String word : str.split(" ")){
            word = word.trim();
            if(!word.isBlank()){
                wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
            }
        }

        String result = "";
        int max = 0;

        for(Map.Entry<String, Integer> entry : wordsMap.entrySet()){
            if(entry.getValue() > max){
                max = entry.getValue();
                result = entry.getKey();
            }
        }
        return result;
    }
}
```

진짜 졸라 지독하다. 마지막 테스트 케이스에서 걸려서 통과를 못해 왜 ... !  
뭐가 문제일까   
처음부터 공백이나 구두점(쉼표, 마침표)를 기준으로 split()으로 전처리하고 시작해야되나 ?  
아오 아오 아오😡

<img src = "https://github.com/user-attachments/assets/94463ad5-178e-45e2-aaea-4bf62b70e2d1" width = 60%>

---

## 답지 보고 힌트 얻기
### 문자열 전처리(데이터 클렌징)
```java
        String[] words = paragraph.replaceAll("\\W+", " ").toLowerCase().split(" ");
```
- `StringBuilder`를 만들어서 `for`문으로 `paragraph`를 검사해서 문자가 아닌 값은 제외하고  
문자만 `append()`하고 이걸 다시 `String`으로 변경해서 금칙어를 삭제하고 ... . . . ...
이런 과정을 위에 한 줄로 끝내버린다🫠

📍 `replace()`
- 문자열 또는 문자를 치환
  정규 표현식❌, 단순한 문자열 치환

📍 `replaceAll()`
- 정규 표현식을 사용해 문자열을 치환
- 첫 번째 인자로 전달된 정규 표현식에 일치하는 모든 부분을 변경
- `\W` : 단어 문자가 아닌 것
- `+` : 연속적인 값
-  `\W+` : 연속적으로 단어 문자가 아닌 값
-  `replaceAll("\\W+", " ")` : 당너가 아닌 문자를 하나 이상 연속해서 찾아 공백으로변경

--- 
## 2트 *- 성공❤️*
```java
class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        StringBuilder sb = new StringBuilder();

        for(char c : paragraph.toLowerCase().toCharArray()){
            if(!(Character.isLetter(c) || Character.isWhitespace(c))){
                c = ' ';
            }
            sb.append(c);
        }

        String str = sb.toString();

        for(String ban : banned){
            str = str.replace(ban, " ");
        }

        Map<String, Integer> wordMap = new HashMap<>();

        for(String word : str.split(" ")){
            word.trim();
            if(!word.isBlank()){
                wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
            }
        }

        int max = 0;
        String result = "";

        for(Map.Entry<String, Integer> entry : wordMap.entrySet()){
            if(entry.getValue() > max){
                max = entry.getValue();
                result = entry.getKey();
            }
        }
        return result;
    }
}
```
## ✨ 우여곡절
### 1트에서 문제가 된 부분
- 입력으로 주어진 문자열을 전처리(구두점 제거)하는 과정에서
bbbc가 제대로 `split`되지 않고, 하나의 단어로 묶여 테스트 통과❌
```text
"a, a, a, a, b,b,b,c, c"
```

### 해결
- 1트 마지막 테스트 케이스에서 문제가 됐던 부분을 참고해  
문자열을 전처리하거나, 금칙어를 제외할 때 공백(`" "`, `' '`)으로 치환했다.

```java
        for(char c : paragraph.toLowerCase().toCharArray()){
            if(!(Character.isLetter(c) || Character.isWhitespace(c))){
                c = ' ';
            }
            sb.append(c);
        }
```
```java
        for(String ban : banned){
            str = str.replace(ban, " ");
        }
```
- 그리고 이후에 Map에 단어를 넣을 때 공백을 기준으로 단어를 나누고, `trim()`을 사용해  
  문자 단어만이 Map에 담기도록 헀다.

```java
        for(String word : str.split(" ")){
            word.trim();
            if(!word.isBlank()){
                wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
            }
        }
```
### 결과
- 썩 좋은 지표는 아니지만 이게 웬 떡이냐 ..!

<div>
    <img src  = "https://github.com/user-attachments/assets/37b77839-0e70-4036-be29-b33d55b28fbb">
</div>

### 배운 것
- `Character.isWhitespace(char c)` : 공백 검사 시 사용
- `getOrDefault(Object key, V defaultValue)` : key에 해당하는 값이 존재하면 그 값을 반환, 존재하지 않으면 기본값(defaultValue)을 반환
- 향상된 `for`문의 대상 : 향상된 `for`문은 `Iterable` 또는 `Iterator`를 구현한 객체를 순회 가능
  - `for(타입 변수 : 컬렉션)` 형태일 때, 컬렉션이 `Iterable`을 구현하고 있으면 순회 가능
  - 배열, `List`, `Set`, `Map.entrySet()` 같은 컬렉션 순회 가능

### 회고
- 풀이에서는 정규식을 사용했지만, 아직 정규식은 익숙하지 않다.  
  성능 측면에서 정규식이 유리하다면 정규식 사용을 고려해봐야겠징  
  그리고 정규식 사용에 능숙해진다면 꽤 수월하게 풀 수 있는 문제들이 많을 것 같다.
- `for`문 4번 사용하는게 맞나 ,, ? 고민해보자
- 불필요한 StringBuiler와 String 변수를 생성한건 아닌지 고민해보자
- 안풀리는 문제는 며칠 묵혀놨다가 풀고 싶을 때 풀면 잘 풀리는거 같기도 ... ?

  ---
  어쨋든 풀어냈다 ...! 키킼
  
  <div>
      <img src ="https://i.pinimg.com/736x/03/a3/85/03a385816a3d931476d5cd9e80b973e4.jpg" width = 30%>
  </div>
