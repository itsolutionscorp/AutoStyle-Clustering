def combine_anagrams(words)
  hash = Hash.new
  words.each do |e|
    key = e.downcase.chars.sort
    hash.has_key?(key) ? ((hash[key] << e)) : (hash[key] = [e])
  end
  array = Array.new
  hash.each_value { |e| array.push(e) }
  array
end