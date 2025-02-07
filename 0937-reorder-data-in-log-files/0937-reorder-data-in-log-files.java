class Solution {
    public String[] reorderLogFiles(String[] logs) {

        // 1. 문자 로그 리스트와 숫자 로그 리스트를 분리
            // 이후에 숫자 로그 리스트를 문자 로그 리스트 뒤에 추가한다.
        
        List<String> letterLogs = new ArrayList<>();
        List<String> digitLogs = new ArrayList<>();

        // 2. logs에서 문자 로그와 숫자 로그를 분류
            // 식별자는 영향을 미치지 않도록 한다.
            // split으로 로그를 분리하고 1번 인덱스가 문자인지 숫자인지 판별한다.

        for(int i = 0; i < logs.length; i++){
            String[] log = logs[i].split(" ");
            char c = log[1].charAt(0);

            // 식별자를 제외한 두 번째 단어가 숫자인 경우 숫자 로그, 문자인 경우 문자 로그
            if(Character.isDigit(c)) { 
                digitLogs.add(logs[i]); 
            }
            else { 
                letterLogs.add(logs[i]); 
            }

        }

        // 3. 문자 로그를 사전순으로 정렬
            // 식별자는 정렬에 영향을 주지 않도록 해야한다.
            // 로그 내용이 동일한 경우 식별자 순으로 정렬

            // \U0001f4a1 문자로그를 식별자와 식별자가 아닌 부분으로 분리한다.
            // \U0001f4a1 식별자가 아닌 부분의 내용이 같으면 식별자로 정렬 순서를 조정한다.

        // Collections.sort()는 반환타입 void 
        Collections.sort(letterLogs, new Comparator<String>(){
            public int compare(String l1, String l2){

                // 각 로그를 식별자와 내용으로 분리
                String[] log1 = l1.split(" ", 2);
                String[] log2 = l2.split(" ", 2);

                // 두 로그의 내용을 비교
                int result = log1[1].compareTo(log2[1]);

                // 로그의 내용이 같으면 식별자로 비교
                if(result == 0){
                    log1[0].compareTo(log2[0]);
                }

                return result;
            }
        });

        // 4. 숫자 로그는 입력 순서대로 
            // 정렬된 letterLogs에 digitLogs를 추가

        letterLogs.addAll(digitLogs);

        // List -> String[]
        String[] result = letterLogs.toArray(new String[0]);
        return result;
    }
}