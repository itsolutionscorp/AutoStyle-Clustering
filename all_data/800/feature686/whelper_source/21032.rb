def combine_anagrams(words)
  hash = Hash.new { |h, k| h[k] = [] }
  words.each do |word|
    key = word.downcase.chars.sort.join
    (hash[key] << word)
  end
  hash.values
end

