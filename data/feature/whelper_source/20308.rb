def combine_anagrams(words)
  hash_words = Hash.new { |h, k| h[k] = [] }
  words.each do |w|
    mkey = w.downcase.chars.sort.join
    hash_words[mkey] = (hash_words[mkey] << w)
  end
  hash_words.values
end

