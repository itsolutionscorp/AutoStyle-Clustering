
def combine_anagrams(words)
  word_count = {}
  words.each do |w|
    process_word = w.downcase.chars.sort.join
    word_count[process_word] = [] unless word_count.has_key?(process_word)
    word_count[process_word] << w    
  end  
  result = []
  word_count.each_value do |value|
    result << value
  end
  return result
end

#print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])