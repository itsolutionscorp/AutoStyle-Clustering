def combine_anagrams(words)
  letters = Hash.new;
  words.each { |word| 
    
    if(letters[word.downcase.chars.sort { |a, b| a.casecmp(b) } .join] == nil)
       letters[word.downcase.chars.sort { |a, b| a.casecmp(b) } .join] = Array.new;
    end
    
    letters[word.downcase.chars.sort { |a, b| a.casecmp(b) } .join] << word;
    
    }
    
    return letters.values;
end

#answer = combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']);

#answer.each {|value|
#  puts value
#  puts "**" 
#  }
