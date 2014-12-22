def combine_anagrams(words)
  h = Hash.new([])
  words.each_with_object(h) do |word, hash|
    string = word.downcase.chars.sort.join("").delete(" ")
    hash[string] += [word]
  end
  h.values
end

