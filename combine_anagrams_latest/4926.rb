def combine_anagrams(words)
  hash = Hash.new

  words.each do |word|
    sum = word.downcase.sum
    hash[sum] = Array.new if hash[sum].nil?
    hash[sum] << word
  end

  hash.values
end