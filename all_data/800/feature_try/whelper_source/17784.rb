def combine_anagrams(words)
  group_hash = Hash.new
  words.map do |x|
    key = x.downcase.chars.sort.join
    value = group_hash[key]
    value ? ((value << x)) : (value = [x])
    group_hash.store(key, value)
  end
  group_hash.values
end

