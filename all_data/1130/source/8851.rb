def combine_anagrams(words)
  hash = {}
  words.each{|element|
    sortedStr = element.downcase().split(//).sort().join
    if hash.has_key? sortedStr
      hash[sortedStr] += [element]
    else
      hash[sortedStr] = [element]
    end
  }
  result = []
  hash.each{|key, element| result += [element]}
  return result
end