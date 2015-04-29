def combine_anagrams(words)
  anagrams = {}
  words.each do |w|
    normalized = w.downcase.split("").sort.join
    if anagrams.has_key?(normalized) then
      anagrams[normalized][anagrams[normalized].length] = w
    else
      anagrams[normalized] = [w]
    end
  end
  result = []
  anagrams.each_key do |normalized|
    result[result.length] = anagrams[normalized]
  end
  result
end