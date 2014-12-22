def combine_anagrams(words)
  result = Array.new
  while (words.size != 0) do
    string = words[0]
    subArray = takeAnagrams(string, words)
    words = (words - subArray)
    result.insert(-1, subArray)
  end
  result
end