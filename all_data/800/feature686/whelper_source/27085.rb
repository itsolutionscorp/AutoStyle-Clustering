def combine_anagrams(words)
  map = Hash.new
  words.each do |word|
    key = word.upcase.chars.sort.join
    map[key].nil? ? (map[key] = Array[word]) : ((map[key] << word))
  end
  adapter = Array.new
  map.each_value { |value| (adapter << value) }
  return adapter
end

