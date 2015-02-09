def combine_anagrams(words)
  hashmap = {}
  result = []
  words.each do |word|
    puts(word)
    key = word.downcase.each_char.sort.join
    if (hashmap[key] == nil) then
      hashmap[key] = result.length
      result[result.length] = [word]
    else
      result[hashmap[key]] += [word]
    end
  end
  return result
end