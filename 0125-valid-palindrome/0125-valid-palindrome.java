class Solution {
    public boolean isPalindrome(String s) {        
        
        // 1. 공백제거, 소문자변환
        String str = s.trim().toLowerCase();

        // 2. 빈 문자열 생성 -> append() 사용을 위해 StringBuilder
        StringBuilder sb = new StringBuilder();

        // 3. for문을 돌면서 영문자, 숫자만 StringBuillder에 append
        for(char c : str.toCharArray()){
            if(Character.isLetterOrDigit(c)){
                sb.append(c);
            }
        }

        int len1 = sb.length();
        int len2 = len1 - 1;
        
        // 4. 팰린드롬 확인
        for(int i = 0; i < len1; i++){
            if(sb.charAt(i) == sb.charAt(len2)){
                len2--;
                continue;
            } else{
                return false;
            }
        }
    
        return true;

    }
}