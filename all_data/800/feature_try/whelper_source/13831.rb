def combine_anagrams(words)
  temp_hash = Hash.new([])
  words.each do |a_word|
    sorted_word = a_word.downcase.split(/(.{1})/).sort.join
    if temp_hash.has_key?(sorted_word) then
      (temp_hash[sorted_word] << a_word)
    else
      temp_hash[sorted_word] = [a_word]
    end
  end
  p(temp_hash)
  return_array = Array.new
  temp_hash.each { |key, value| (return_array << value) }
  return_array
end

