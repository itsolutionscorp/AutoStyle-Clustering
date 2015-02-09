def combine_anagrams(words)
  anagrams = Array.new
  while (words.length > 0) do
    word = words.shift
    words.delete(word)
    each_anagrams = [word]
    get_anagrams(each_anagrams[0].downcase.chars.sort.join, words) do |str|
      (each_anagrams << str)
      words.delete(str)
    end
    (anagrams << each_anagrams)
  end
  anagrams
end