def combine_anagrams(words)

   combined_hash = Hash.new
   
   # Returns the form: "hash of letter count" => "word"
   words.each do |word|
      
      letter_count_key = count_letters( word )
      
      if combined_hash.key?( letter_count_key )
         
         combined_hash[letter_count_key].push( word )
         
      else
      
         combined_hash[letter_count_key] = Array.new.push( word )
      
      end
      
   end
      
   return combined_hash.values
   
end

# Returns the number of times each letter in the word appears
def count_letters(word)

  letters = Hash.new

  word.gsub( /([a-z])/i ) do |letter|

    letter.downcase!

    if letters.key?( letter )
      letters[letter] = letters[letter] + 1
    else
      letters[letter] = 1
    end

  end
  
  return letters

end
#print input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'], "\n"
#print "\n\nResult:\n", combine_anagrams(input)