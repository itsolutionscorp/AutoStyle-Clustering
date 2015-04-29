def combine_anagrams(words)
  anagrams = Hash.new
  words.each do
    |word|
    key = word.downcase.chars.sort.join
    if ! anagrams.has_key? key
    then
      anagrams[key] = Array.new
    end
    anagrams[key].push(word)      
  end
  result = Array.new
  anagrams.each do
    |group|
    result.push(group[1])
  end
  return result
end
