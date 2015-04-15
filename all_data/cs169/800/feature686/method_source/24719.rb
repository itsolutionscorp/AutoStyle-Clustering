def combine_anagrams(words)
  anagrams = []
  i = 0
  indices = []
  match = []
  while (words.length > 0) do
    anagrams.push([words.shift])
    anagram = anagrams[i].first.downcase.split("").sort.join("")
    puts(anagram)
    words.each_with_index do |w, idx|
      if (w.downcase.split("").sort.join("") == anagram) then
        (match << w)
        anagrams[i] += match
      end
    end
    anagrams[i].flatten
    anagrams[i].uniq!
    while (match.length > 0) do
      words.delete(match.shift)
    end
    i = (i + 1)
  end
  anagrams
end