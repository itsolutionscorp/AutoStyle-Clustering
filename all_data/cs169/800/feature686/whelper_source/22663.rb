def combine_anagrams(words)
  anagrams = {}
  words.each do |w|
    sorted = w.downcase.chars.sort.join
    anagrams[sorted] ||= []
    anagrams[sorted].push(w)
  end
  anagrams.values
end

