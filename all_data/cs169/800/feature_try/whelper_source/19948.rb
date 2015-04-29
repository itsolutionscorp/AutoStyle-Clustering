def findAnagram(anagrams, word)
  found = false
  (0...anagrams.size).each do |idx|
    return [true, idx] if (compareAnagram(anagrams[idx][0], word) == true)
  end
  return [false, -1]
end

def combine_anagrams(words)
  res = []
  words.each do |word|
    found, idx = findAnagram(res, word)
    (found == false) ? ((res << [word])) : ((res[idx] << word))
  end
  res
end

