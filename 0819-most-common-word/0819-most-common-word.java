class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        // 1. 주어진 문자열 전처리
            // 대소문자 구분X -> 모두 소문자로
            // 구두점(마침표, 쉼표) 무시 -> ' ' 공백으로 

        StringBuilder sb = new StringBuilder();

        for(char c : paragraph.toLowerCase().toCharArray()){
            // 문자가 아니거나, 공백이 아닌경우 공백으로 치환
            if(!(Character.isLetter(c) || Character.isWhitespace(c))){
                c = ' ';
            }
            sb.append(c);
        }

        // 2. StringBuilder를 String으로 굽는다.
            // String의 replace()를 사용하기 위함
            // StringBuilder의 replace()와는 다르다.
        String str = sb.toString();

        // 3. 금칙어를 제외
        for(String ban : banned){
            str = str.replace(ban, " ");
        }

        // 4. 단어와 단어 빈도를 카운트할 수 있는 맵 생성
        Map<String, Integer> wordMap = new HashMap<>();

        // 5. 맵에 단어와 단어 빈도를 채워넣는다.
        for(String word : str.split(" ")){
            word.trim();
            if(!word.isBlank()){
                wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
            }
        }

        // 6. 가장 빈도가 높은 단어를 찾아 출력한다.
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