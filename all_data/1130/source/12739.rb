#HW 1: Ruby calisthnics
#Author: Anand Kapoor

#Part 3 - Anagrams

def find_elements(element_name, element_array, original_array)
  @result_index = []
  element_array.each_index { |e| @result_index << original_array.at(e) if element_array.at(e) == element_name.to_s }
  return @result_index
end


def combine_anagrams(words)
  @result = []
  @anagrams = []

  words.each do |word|
    @sorted_word = []
    @key = ""
    word.to_s.gsub(%r\([a-zA-Z])\) {|character| @sorted_word << character.to_s.downcase}
    @sorted_word.sort.each {|x| @key << x}
    @anagrams << @key
  end
  @anagrams_unique = @anagrams.uniq
  @anagrams_unique.each {|e| @result << find_elements(e, @anagrams, words) }
  return @result.to_a
end
combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream'])