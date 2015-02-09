
def combine_anagrams(words)
  group_hash = Hash::new()
  words.map do |x|
    key = x.downcase.chars.sort.join
    value = group_hash[key]
    if value
      value << x;
    else
      value = [x];
    end
    group_hash.store(key, value)
  end
  group_hash.values;
end


combine_anagrams(['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'Scream', 'scream'])
