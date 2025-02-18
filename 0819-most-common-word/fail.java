class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        // 1. 주어진 문자열을 소문자로 변환한다.
            // 구두점(마침표, 쉼표)를 무시한다 -> 삭제한다
        StringBuilder sb = new StringBuilder();
        
        for(char c : paragraph.toLowerCase().toCharArray()){
            if(Character.isLetter(c) || c == ' '){
                sb.append(c);
            }
        }

        // StringBuilder를 String으로 굽는다.
        String str = sb.toString();

        // 2. 금칙어를 제외한다.
            // 문자열(str)에 금칙어가 포함되어 있으면 삭제

        for(String ban : banned){
            str = str.replace(ban, "");
        }

        // 3. 단어 빈도를 카운트하는 맵 생성
        Map<String, Integer> wordsMap = new HashMap<>();
        for(String word : str.split(" ")){
            word = word.trim();
            if(!word.isBlank()){
                wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
            }
        }

        // 4. 가장 빈도가 높은 단어를 찾는다.
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
