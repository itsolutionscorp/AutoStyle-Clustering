#anagrams                                                                                                                                                                                                                    
 def combine_anagrams(words)                                                                                                                                                                                                  
    anagramHash=Hash.new(0)                                                                                                                                                                                                   
    words.each{|word|                                                                                                                                                                                                         
            if (anagramHash.has_key?(word.downcase.chars.sort{|a,b|a.casecmp(b)}.join))                                                                                                                                        
                a = anagramHash[word.downcase.chars.sort{|a,b|a.casecmp(b)}.join]                                                                                                                                               
                a << word                                                                                                                                                                                                       
                anagramHash[word.downcase.chars.sort{|a,b|a.casecmp(b)}.join]=a                                                                                                                                                 
             else                                                                                                                                                                                                               
                anagramHash[word.downcase.chars.sort{|a,b|a.casecmp(b)}.join]=[word]                                                                                                                                            
             end}                                                                                                                                                                                                               
     return anagramHash.values                                                                                                                                                                                              
 end                                                                                                                                                                                                                          
 #list=["race","care","creams","scream","cars","rcas","racs","scar","potatoes"]                                                                                                                                                
 #puts combine_anagrams(list)   