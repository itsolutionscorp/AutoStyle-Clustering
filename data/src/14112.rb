def combine_anagrams(words)
  anagram = Array.new
  while (words.length > 0) do
    group = Array.new
    first = words[0]
    words.each do |word|
      if (first.downcase.chars.sort.join == word.downcase.chars.sort.join) then
        (group << word)
      end
    end
    (anagram << group)
    words.reject! do |word|
      (first.downcase.chars.sort.join == word.downcase.chars.sort.join)
    end
  end
  return anagram
end