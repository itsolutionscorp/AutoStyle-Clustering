def combine_anagrams(words)

  anagram_list = Hash.new()

  words.each { |w| 
    sorted = w.downcase.split('').sort.join
    puts sorted
    if anagram_list[sorted] == nil
      anagram_list[sorted] = [w]
    else 
      anagram_list[sorted] << w
    end
  }

  output = []
  anagram_list.each_value { |v| output << v }

  return output


end

#combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 

