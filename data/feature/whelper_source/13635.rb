def combine_anagrams(words)
  dict = Hash.new([])
  words.each do |word|
    dict[word.strip.downcase.split(/\s*/).sort.join] += [word]
  end
  anagrams = dict.values
end

