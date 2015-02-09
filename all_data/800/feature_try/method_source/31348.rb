def combine_anagrams(words)
  hash = Hash.new
  words.each do |word|
    sorted_word = word.downcase.chars.sort.join
    hash[sorted_word] = Array.new if (not hash.include?(sorted_word))
    (hash[sorted_word] << word)
  end
  result = Array.new
  hash.each_value { |word| (result << word) }
  return result
end