def combine_anagrams(array)
  sorted_words = {}
  
  # clean string
  array.each do |raw_word|
    temp_array = []
    raw_word.downcase.each_char {|c| temp_array << c}
    sorted_word = temp_array.sort.join
    
    if sorted_words.has_key?(sorted_word)
      sorted_words[sorted_word] << raw_word
    else
      sorted_words[sorted_word] = [raw_word]
    end
  end
  output = []
  sorted_words.each {|key, array| output << array } 
  return output
end
