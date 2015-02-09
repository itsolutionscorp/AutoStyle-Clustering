def combine_anagrams(words)
  answer_hash = Hash.new([])
  words.each do |word|
    answer_hash[word.downcase.chars.sort] = (answer_hash[word.downcase.chars.sort] + [word])
  end
  answer_hash.values
end

