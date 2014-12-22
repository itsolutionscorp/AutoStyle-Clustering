def combine_anagrams(words)
  hash = Hash.new
  words.each do |word|
    normalized = word.downcase.chars.sort.join
    if (hash[normalized] == nil)
      hash[normalized] = Array.new
    end
    hash[normalized]<<word
  end
  array = Array.new
  hash.each_value { |value| array<<value }
  return array
end
