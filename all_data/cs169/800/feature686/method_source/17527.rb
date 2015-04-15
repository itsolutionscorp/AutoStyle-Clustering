def combine_anagrams(words)
  dictionary = Hash.new
  words.each do |w|
    key = w.downcase.chars.sort.join
    dictionary[key] = [] if (not dictionary.has_key?(key))
    dictionary[key].push(w)
  end
  output = []
  dictionary.each_value { |v| output.push(v) }
  output
end