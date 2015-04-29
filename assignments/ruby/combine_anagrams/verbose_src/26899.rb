 #words_dc = words.map(&:downcase)  
def combine_anagrams(words)
  
  found = 0
  
  words_added = Array.new
  words_group = Array.new

  something_added = false
      group_id = 0;
  words.each{ |word| 
    permut = word.downcase.chars.to_a.permutation.map &:join
      permutation_group = Array.new
            element_id = 0;
    permut.each { |valid| 


      words.each { |valid_word|
        word_dc = valid_word.downcase
        if valid == word_dc
          word_found = words.fetch(words.index(valid_word)) 
          if !words_added.include?(word_found)
            words_added[found] = word_found

            permutation_group[element_id] = word_found   
            element_id = element_id + 1 
            found = found + 1
            something_added = true

            # puts group_id
            # puts element_id
            #puts word_found
          end
          
          
        end
      }
    }
    if something_added
       words_group[group_id] = permutation_group
       group_id = group_id + 1;
       something_added = false
    end
  }
  return words_group
end

words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']


puts combine_anagrams(words)
