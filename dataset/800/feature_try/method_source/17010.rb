def combine_anagrams(words)
  result = []
  words.each do |word|
    found = false
    result.each do |search|
      if (search[0].downcase.split("").sort == word.downcase.split("").sort) then
        search.push(word)
        found = true
        break
      end
    end
    result.push([word]) if (not found)
  end
  return result
end