def combine_anagrams(words)
  hash = {}
  words.each do |x|
    index = x.downcase.split(//).sort
    val = hash[index]
    val ? (hash[index] = (val + Array[x])) : (hash[index] = Array[x])
  end
  return hash.values
end

