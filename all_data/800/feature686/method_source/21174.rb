def combine_anagrams(words)
  lookup = []
  result = []
  words.each do |word|
    if lookup.include?(word.downcase.split(//).sort) then
      result[lookup.index(word.downcase.split(//).sort)].push(word)
    else
      lookup.push(word.downcase.split(//).sort)
      result[lookup.index(word.downcase.split(//).sort)] = []
      result[lookup.index(word.downcase.split(//).sort)].push(word)
    end
  end
  return result
end