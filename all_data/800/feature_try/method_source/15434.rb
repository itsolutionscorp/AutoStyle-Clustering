def combine_anagrams(words)
  def same?(a, b)
    (a.downcase.chars.sort.join == b.downcase.chars.sort.join)
  end
  anagrams = []
  while (words.length > 0) do
    a = words.delete_at(0)
    temp = [a]
    words.each do |word|
      (temp << words.slice!(words.index(word))) if same?(a, word)
    end
    words.each do |word|
      (temp << words.slice!(words.index(word))) if same?(a, word)
    end
    words.each do |word|
      (temp << words.slice!(words.index(word))) if same?(a, word)
    end
    (anagrams << temp)
  end
  return anagrams
end