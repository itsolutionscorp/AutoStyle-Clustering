def combine_anagrams(words)
  out = []
  while (words.length > 0) do
    newwords = []
    anagram = [words[0]]
    test = words[0].downcase.split(//).sort.join("")
    (1...words.length).each do |i|
      if (test == words[i].downcase.split(//).sort.join("")) then
        (anagram << words[i])
      else
        (newwords << words[i])
      end
    end
    words = newwords
    (out << anagram)
  end
  return out
end