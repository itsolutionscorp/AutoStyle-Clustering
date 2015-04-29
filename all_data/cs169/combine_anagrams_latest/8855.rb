def combine_anagrams(words)
  anagrams=Array.new()
  words = words.flatten.sort_by{|word| word.size }
  
  word1 = words.delete_at(1)
  if words.size >0
    anagrams =extract_anagrams(anagrams,word1 ,words)
  end

  puts anagrams.size
  puts anagrams

 return anagrams

end


def extract_anagrams(anagrams, word1, words)

  puts word1
 puts anagrams.size
  puts anagrams

  anagram_collection =Array.new.push(word1) 
  if words.size >0  
    i =0  
    while  i < words.size 
       word2 = words.values_at(i)[0]
      if word2.size==word1.size and anagrama?(word1.inspect.downcase, word2.inspect.downcase) 
        anagram_collection.push(word2) 
        words.delete_at(i) 
                                   
      else 
          i +=1
      end         
     end
  
     anagrams<<anagram_collection
     word1 = words.delete_at(0)
     if word1 != nil
     anagrams = extract_anagrams(anagrams,word1,words)  
     else
       puts 'Wor1==nill'
     end
    # if words.size > 0   
         
     # elsif words.size==0
     else
   
        puts word1
        puts anagrams.size  
        anagrams<<Array.new.push(word1)  
       puts anagrams

   #  end  
     end
 
       puts anagrams
  
     return  anagrams
end

def anagrama?(word1, word2) 
    return word1.split(//).sort == word2.split(//).sort
end


  combine_anagrams([["HeLLo", "hello"]])
# combine_anagrams([["cars", "cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream", "scream"]])
# combine_anagrams([[ "cars", "scream","racs"], ["four"], ["for"], ["potatoes","cars"], ["creams", "scar" ], ["HeLLo", "hello"]])
  # , "kokb", "fdghtys", "bdgdhskcbd", "dgfjr", "rsf", "frs", "djsg", "svpnr"