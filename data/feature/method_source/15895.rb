def combine_anagrams(words)
  dict = {}
  dict.default = []
  words.each do |word|
    sorted = word.downcase.chars.sort.join
    dict[sorted] = (dict[sorted] + [word])
  end
  dict.values
end