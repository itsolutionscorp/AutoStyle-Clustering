
require 'pp'

def combine_anagrams(words)

  count = 0
  result = []

  while (words.length != 0)
    
    current = words[0].downcase.chars.sort { |a, b| a.casecmp(b) } .join

    group = []

    group << words[0]
    words.delete_at(0)

    words.each_with_index {

      |key, index|

      compareWord = key.downcase.chars.sort { |a, b| a.casecmp(b) } .join

      if(current == compareWord)
        group << words[index]
        words.delete_at(index)
      end

    }
    
    result << group

    count += 1
  
  end

  return result
end

words = ['a', 'A', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

pp combine_anagrams(words) 
    