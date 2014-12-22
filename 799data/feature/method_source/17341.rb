def combine_anagrams(words)
  anagrama = Array.new
  words.each do |m|
    ar = words.select do |n|
      (m.downcase.chars.sort.join == n.downcase.chars.sort.join)
    end
    anagrama.push(ar) if (not anagrama.include?(ar))
  end
  return anagrama
end