def combine_anagrams(words)
  anagrams = []
  while (words.length > 0) do
    group = [words.delete_at(0)]
    if (words.length > 0) then
      words = words.reject do |word|
        if (group[0].downcase.chars.sort == word.downcase.chars.sort) then
          (group << word)
          true
        else
          false
        end
      end
    end
    (anagrams << group)
  end
  anagrams
end

