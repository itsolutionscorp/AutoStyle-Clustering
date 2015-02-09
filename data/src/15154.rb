def combine_anagrams(words)
  combined_words = {}
  words.each do |word|
    normalized = word.downcase.split("").sort.join
    if combined_words.has_key?(normalized) then
      (combined_words[normalized] << word)
    else
      combined_words[normalized] = [word]
    end
  end
  result = []
  combined_words.each { |key, group| (result << group) }
  return result
end