def combine_anagrams(words)
  output_array = Array.new(0)
  words.each do |word_1|
    temp_array = []
    words.each do |word_2|
      if (word_2.downcase.split(//).sort == word_1.downcase.split(//).sort) then
        temp_array.push(word_2)
      end
    end
    output_array.push(temp_array)
  end
  return output_array.uniq
end