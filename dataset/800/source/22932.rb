# W1 Part3 anagrams

def combine_anagrams(words)

    anagrame= words.group_by {|word| word.downcase.chars.sort}
    
    
    puts "anagrames="
    p anagrame.values   
  
  

end

a=['cars','for','potatoes','racs','four','scar','creams','scream']


combine_anagrams(a)


