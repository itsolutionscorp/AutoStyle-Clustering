def combine_anagrams(words)
  anagrams = words.group_by { |word| word.downcase.chars.sort }.values
  anagrams
end

puts combine_anagrams(["cars", "for", "potatoes", "racs", "four", "scar", "creams", "scream"]).inspect
puts combine_anagrams(["pear", "rats", "reap", "eat", "ate", "stars", "tars"]).inspect
puts combine_anagrams(["A", "b", "a"]).inspect

puts combine_anagrams(["HeLLo", "hello", "what"]).inspect