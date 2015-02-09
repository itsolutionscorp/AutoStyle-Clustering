def combine_anagrams(words)
  annagrams = Hash.new
  words.each do |word|
    sorted = word.downcase.split("").sort.join
    annagrams[sorted] = Array.new unless annagrams.has_key?(sorted)
    (annagrams[sorted] << word)
  end
  annagrams.values
end

