def combine_anagrams(words)
  coll = words.inject({}) do |groups, word|
    key = word.downcase.split('').sort.join('').to_sym
    groups[key.to_sym] ||= []
    groups[key.to_sym] << word

    groups
  end

  coll.inject([]) do |ret, c|
    ret << c[1]
  end
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).inspect
