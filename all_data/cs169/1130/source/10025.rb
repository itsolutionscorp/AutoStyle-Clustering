def combine_anagrams(words)
  res = Array.new
  used = Set.new
  words.each do |word|
    if !used.include?(word)
      parts = words.partition { |w| w.downcase.chars.sort == word.downcase.chars.sort }      
      res.push(parts[0])
      used.merge(parts[0])
    end
  end
  res
end
