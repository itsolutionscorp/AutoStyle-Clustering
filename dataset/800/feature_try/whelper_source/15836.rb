def combine_anagrams(words)
  return words.collect do |word|
    words.select do |x|
      (x.downcase.chars.sort { |a, b| a.casecmp(b) }.join == word.downcase.chars.sort { |a, b| a.casecmp(b) }.join)
    end
  end.uniq
end

