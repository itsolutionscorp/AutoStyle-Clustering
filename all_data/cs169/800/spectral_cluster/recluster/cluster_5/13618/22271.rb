     def combine_anagrams(words)
       tempWords = words.sort
       hashWords = Hash.new
       tempWords.each {|tempString| 
         stringSortedChars = sorted_char_array(tempString)
         
         if hashWords.has_key?stringSortedChars
           tempArray = Array.new
           tempArray = hashWords.fetch(stringSortedChars)
           tempArray.push(tempString)
         else
           tempArray = Array.new
           tempArray[0] = tempString
           hashWords[stringSortedChars] = tempArray
         end
       }
       arrayResult = hashWords.values
       return arrayResult
     end
     def sorted_char_array(tempString)
         tempArray=Array.new
         i=0
         tempString.each_char{|tempChar|
           tempArray[i]=tempChar.downcase
           i+=1
         }
         tempArray = tempArray.sort
         strSortedCharString = ""
         tempArray.each {|charAtIndex| strSortedCharString+=charAtIndex}
         return strSortedCharString
     end
