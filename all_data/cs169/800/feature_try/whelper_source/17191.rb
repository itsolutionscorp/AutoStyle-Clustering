def anagramcmp(w1, w2)
  return w1.chars.sort { |a, b| a.casecmp(b) }.join.casecmp(w2.chars.sort { |a, b| a.casecmp(b) }.join)
end

def combine_anagrams(words)
  return words if words.empty?
  sorted = words.each.sort { |w1, w2| anagramcmp(w1, w2) }
  anagrams = Array.new
  anagram = sorted[0]
  i = 0
  (anagrams << Array.new)
  anagrams[0] = Array.new
  sorted.each do |w|
    if (anagramcmp(w, anagram) == 0) then
      (anagrams[i] << w)
    else
      i = (i + 1)
      (anagrams << Array.new)
      anagram = w
      (anagrams[i] << w)
    end
  end
  return anagrams
end

