def combine_anagrams(words)
  uniqe_words = words.map { |word| word.downcase.split("").sort.join }.uniq
  result = Array.new
  uniqe_words.each do |pattern|
    tab = Array.new
    words.each do |word|
      (tab << word) if (pattern == word.downcase.split("").sort.join)
    end
    (result << tab)
  end
  result
end

