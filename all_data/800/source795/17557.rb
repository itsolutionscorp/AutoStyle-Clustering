def combine_anagrams(words)
  buckets = {};
  
  words.each do |word|
    key = word.downcase.chars.sort.join
    if buckets.has_key?(key)
      buckets[key] << word
    else
      buckets[key] = [word]
    end
  end
  
  output = [];
  buckets.each_value do |value|
    output << value
  end
  
  return output
end

#combine_anagrams(input)

