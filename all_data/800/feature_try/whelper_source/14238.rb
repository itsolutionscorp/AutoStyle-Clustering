def combine_anagrams(words)
  dict = {}
  words.each do |word|
    key = word.downcase.chars.sort.join
    dict[key] ||= []
    (dict[key] << word)
  end
  output = []
  dict.each { |key, value| (output << value) }
  output
end

