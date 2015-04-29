def combine_anagrams(words)
  map = {}
  words.each do |w|
    w1 = w.downcase
    key = w1.chars.sort.join
    if (map.has_key?(key) == false) then
      map[key] = w1
    else
      map[key] = [map[key], w1]
    end
  end
  list = []
  map.each_value { |v| list.push(v) }
  return list
end

