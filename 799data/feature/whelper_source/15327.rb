def combine_anagrams(words)
  map = Hash.new
  words.each do |word|
    key = word.downcase.split(//).sort
    map[key] ? ((map[key] << word)) : (map[key] = [word])
  end
  map.collect { |k, v| v }
end

