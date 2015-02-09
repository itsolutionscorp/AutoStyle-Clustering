def combine_anagrams(words)
  
  words_array = Array.new
  
  
  words.each do |word|
    
    belongs_to_group = false
    
    #1. Check if it belongs to the group.
    words_array.each do |group|
      if(sort(group[0]) == sort(word))
        group << word
        belongs_to_group = true
      end
    end
    
    if(!belongs_to_group)
      words_array << [word]
    end
  end
  
  return words_array
  
end

def sort(string)
  
  array = Array.new
  
  string.each_char{|c| array << c}
  
  return array.sort.join.downcase
  
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
