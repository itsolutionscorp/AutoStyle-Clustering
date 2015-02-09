def combine_anagrams(words)
  wordss = words.map { |word| word.downcase.scan(/./).sort.join }.uniq
  result = Array.new
  wordss.map do |word_u|
    result1 = Array.new
    words.map do |word|
      (result1 << word) if (word_u == word.downcase.scan(/./).sort.join)
    end
    (result << result1)
  end
  return result
end