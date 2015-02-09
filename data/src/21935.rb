def combine_anagrams(words)
  wordHash = Hash.new
  words.each do |word|
    hashkey = word.downcase.chars.sort.join
    if wordHash.include?(hashkey) then
      wordHash[hashkey].push(word)
    else
      wordHash[hashkey] = Array[word]
    end
  end
  result = Array.new
  wordHash.each_value { |value| result.push(value) }
  return result
end