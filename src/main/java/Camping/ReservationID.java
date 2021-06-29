package main.java.Camping;

import main.java.Exceptions.InvalidReservationIDException;

import java.util.ArrayList;
import java.util.HashMap;

public class ReservationID {
    private String alphanumericCode;//MUST BE 6 DIGITS!!!
    private static HashMap<Character,Integer> alphabetMap;
    private static ArrayList<Character> alphabetList;


    public ReservationID(String alphanumericCode) {
        this.alphanumericCode = alphanumericCode;
        setup();
    }


    private void setup(){
        alphabetMap.put('0',0);
        alphabetMap.put('1',1);
        alphabetMap.put('2',2);
        alphabetMap.put('3',3);
        alphabetMap.put('4',4);
        alphabetMap.put('5',5);
        alphabetMap.put('6',6);
        alphabetMap.put('7',7);
        alphabetMap.put('8',8);
        alphabetMap.put('9',9);
        alphabetMap.put('A',10);
        alphabetMap.put('B',11);
        alphabetMap.put('C',12);
        alphabetMap.put('D',13);
        alphabetMap.put('E',14);
        alphabetMap.put('F',15);
        alphabetMap.put('G',16);
        alphabetMap.put('H',17);
        alphabetMap.put('I',18);
        alphabetMap.put('J',19);
        alphabetMap.put('K',20);
        alphabetMap.put('L',21);
        alphabetMap.put('M',22);
        alphabetMap.put('N',23);
        alphabetMap.put('O',24);
        alphabetMap.put('P',25);
        alphabetMap.put('Q',26);
        alphabetMap.put('R',27);
        alphabetMap.put('S',28);
        alphabetMap.put('T',29);
        alphabetMap.put('U',30);
        alphabetMap.put('V',31);
        alphabetMap.put('W',32);
        alphabetMap.put('X',33);
        alphabetMap.put('Y',34);
        alphabetMap.put('Z',35);
        alphabetList = (ArrayList<Character>) alphabetMap.keySet();
    }

    public String getAlphanumericCode() {
        return alphanumericCode;
    }

    public void setAlphanumericCode(String alphanumericCode) {
        this.alphanumericCode = alphanumericCode;
    }

    public ReservationID plusOne(ReservationID r) throws InvalidReservationIDException {
        String code = r.getAlphanumericCode();
        String newCode = "";
        char[] characterlist = r.getAlphanumericCode().toCharArray();
        for (int i = 5; i>=0; i--) {
            if(characterlist[i]!='Z'){
                characterlist[i] = alphabetList.get(alphabetMap.get(characterlist[i])+1);
                newCode = characterlist.toString();
                r.setAlphanumericCode(newCode);
                return r;
            }
        }
        throw new InvalidReservationIDException("You ran out of ReservationID's! Please reset the ID's before continuing");
    }
}
