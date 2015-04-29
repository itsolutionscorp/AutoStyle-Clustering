def combine_anagrams(words)
  map = Hash.new(Array.new)
  if !words.empty?
    words.each do |w|
      map[w.downcase.chars.sort.join] += [w]
    end
  end
  map.values.to_s
end
puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream','CARS','RASC']
)
