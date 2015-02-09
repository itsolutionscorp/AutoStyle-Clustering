def combine_anagrams(words)
  anargams = {}
  words.each do |word|
    anagram = word.downcase.chars.sort.join
    if anargams[anagram] then
      anargams[anagram] += [word]
    else
      anargams[anagram] = [word]
    end
  end
  res = []
  anargams.each { |key, value| res.insert(0, value) }
  return res.reverse
end