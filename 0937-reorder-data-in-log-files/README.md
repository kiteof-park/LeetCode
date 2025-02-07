<h2><a href="https://leetcode.com/problems/reorder-data-in-log-files">937. Reorder Data in Log Files</a></h2><h3>Medium</h3><hr><p>You are given an array of <code>logs</code>. Each log is a space-delimited string of words, where the first word is the <strong>identifier</strong>.</p>

<p>There are two types of logs:</p>

<ul>
	<li><b>Letter-logs</b>: All words (except the identifier) consist of lowercase English letters.</li>
	<li><strong>Digit-logs</strong>: All words (except the identifier) consist of digits.</li>
</ul>

<p>Reorder these logs so that:</p>

<ol>
	<li>The <strong>letter-logs</strong> come before all <strong>digit-logs</strong>.</li>
	<li>The <strong>letter-logs</strong> are sorted lexicographically by their contents. If their contents are the same, then sort them lexicographically by their identifiers.</li>
	<li>The <strong>digit-logs</strong> maintain their relative ordering.</li>
</ol>

<p>Return <em>the final order of the logs</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> logs = [&quot;dig1 8 1 5 1&quot;,&quot;let1 art can&quot;,&quot;dig2 3 6&quot;,&quot;let2 own kit dig&quot;,&quot;let3 art zero&quot;]
<strong>Output:</strong> [&quot;let1 art can&quot;,&quot;let3 art zero&quot;,&quot;let2 own kit dig&quot;,&quot;dig1 8 1 5 1&quot;,&quot;dig2 3 6&quot;]
<strong>Explanation:</strong>
The letter-log contents are all different, so their ordering is &quot;art can&quot;, &quot;art zero&quot;, &quot;own kit dig&quot;.
The digit-logs have a relative order of &quot;dig1 8 1 5 1&quot;, &quot;dig2 3 6&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> logs = [&quot;a1 9 2 3 1&quot;,&quot;g1 act car&quot;,&quot;zo4 4 7&quot;,&quot;ab1 off key dog&quot;,&quot;a8 act zoo&quot;]
<strong>Output:</strong> [&quot;g1 act car&quot;,&quot;a8 act zoo&quot;,&quot;ab1 off key dog&quot;,&quot;a1 9 2 3 1&quot;,&quot;zo4 4 7&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= logs.length &lt;= 100</code></li>
	<li><code>3 &lt;= logs[i].length &lt;= 100</code></li>
	<li>All the tokens of <code>logs[i]</code> are separated by a <strong>single</strong> space.</li>
	<li><code>logs[i]</code> is guaranteed to have an identifier and at least one word after the identifier.</li>
</ul>

---
## 로그 파일 재정렬
### 제약 조건
1. 로그 가장 앞부분은 식별자로, 순서에 영향을 끼치지 않는다.
2. 문자로 구성된 로그가 숫자 로그보다 앞에오며, 문자 로그는 사전순으로 한다.
3. 문자가 동일한 경우에는 식별자순으로 한다.
4. 숫자 로그는 입력 순서대로 한다.

```text
입력: ["id1 8 1 5 1", "id2 art can", "id3 3 6", "id4 own kit dig", "id5 art zero"]
출력: ["id2 art can", "id5 art zero", "id4 own kit dig", "id1 8 1 5 1", "id3 3 6"]
```

### ✨ 우여곡절
- 매 순간이 고비였다 .. 다시 풀어봐야겠다.
- 특히 **배열, 컬렉션을 다루는 메서드**를 복습해야겠다. 메서드의 경우 대부분 검색을 통해 사용했다.
- Comparable, Comparator, compare, compareTo를 복습하고 활용하는 방법에 대해 연습이 더 필요하다.
- **문제를 풀 때 단계별로 나눠서 생각하는 연습, 그리고 한 번에 해결하려는 버릇을 고쳐야될 것 같다.**
  - 💡문자 로그와 숫자 로그를 분리해서 작성하는 방향이 크게 도움되었다.
- 쫄지말자 ..! 이걸 어떻게 풀어 하는 순간 말린다!

### 🪄 Step-by-Step Solution
- [x] 문자로 구성된 로그와 숫자로 구성된 로그를 분리한다.
- 문자 로그와 숫자 로그를 분리하기 위해 두 개의 `List`를 생성한다.
```java
List<String> letterLogs = new ArrayList<>();
List<String> digitLogs = new ArrayList<>();
```

- [x] 식별자로 제외한 로그의 내용을 판별해 문자로 구성된 로그인지, 숫자로 구성된 로그인지 분류한다.
- `String[]`에서 하나의 요소(로그)를 가공한다.
  - 식별자는 제외한 로그의 첫 번째 요소가 문자인지 숫자인지 구분한다.
  - 첫 번째 요소가(정확히는 첫 번째 요소의 첫 글자) 문자인 경우 문자 로그 리스트에, 숫자인 경우 숫자 로그 리스트에 추가한다.

```java
for(int i = 0; logs.length; i++{
    // "id2 art can"의 로그인 경우 ["id2", "art", "can"]로 split된다.
    String[] log = logs[i].split(" ");

    // 식별자를 제외한 로그 내용 판별을 위해 1번 인덱스 요소의 0번 문자를 검사한다.
    // "art"의 "a"를 추출
    char c = log[1].chartAt(0);

    // 검사 결과에 맞게 로그 리스트에 추가한다.
    if(Character.isDigit(c)){
        digitLogs.add(logs[i]);
    }
    else {
        letterLogs.add(logs[i]);
    }
}
```
- [x] 숫자로 구성된 로그인 경우(`digitLogs`), 입력 순서대로 정렬하므로 더이상의 추가적인 작업은 불필요하다.
- [x] 문자로 구성된 로그인 경우(`letterLogs`), 사전 순서대로의 정렬이 필요하며 내용이 동일한 경우 식별자로 정렬한다.
  - 하나의 로그를 식별자와 내용으로 분리한다.
  - 우선 내용을 사전순으로 비교하며 정렬하고, 내용이 같은 경우 식별자로 비교해 정렬한다.
  - 💡 정렬의 기준을 직접 정의하는 것이므로 `Comparator`의 `compare()` 메서드를 구현해야한다.
  - 💡 `letterLogs`는 `List`(컬렉션)이므로 `Collections.sort()`를 활용한다.

```java
Collections.sort(letterLogs, new Comparator<String>(){
    public int compare(String l1, String l2){

        // 각 로그를 식별자와 내용으로 분리
        String[] log1 = l1.split(" ", 2);    // ["id2", "art can"]
        String[] log2 = l2.split(" ", 2);

        // 식별자를 제외하고 두 로그의 내용을 비교한다.
        // 💡이 때, 로그의 내용은 문자열(String)이므로 compareTo()를 이용한다.
        int result = log1[1].compareTo(log2[1]);

        // 로그의 내용이 같으면 식별자로 비교한다.
        if(result == 0){
            result = log1[0].compareTo(log2[0]);
        }

        return result;
      }
});
```
- [x] 숫자 로그는 입력 순서대로 정렬하므로, 정렬된 `letterLogs`에 `digitLogs`를 추가한다.

```java
letterLogs.addAll(digitLogs);
```

- [x] `class Solution{..}`의 반환타입이 `String[]`이므로 `List`를 `String[]`으로 변환한다.
```java
String[] resultLog = letterLogs.toArray(new String[0]);
return resultLog;
```

### 결과

![image](https://github.com/user-attachments/assets/345e664b-e4f2-47c4-bd37-81d8e0fb847b)

 


