def combine_anagrams(words)
  hash = Hash.new
  words.each do |word|
    key = word.downcase.split("").sort.join
    hash[key] = Array.new if (hash[key] == nil)
    array = hash[key]
    array.push(word)
    hash[key] = array
    array = nil
  end
  return hash.values
end