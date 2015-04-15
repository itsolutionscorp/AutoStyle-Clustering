def combine_anagrams(words)
  result = Array.new
  words.each do |word|
    if result == [] then result += [[word]]
    else
      result = helper(word, result)
    end
  end
  return result
end

def helper(word, result)
  i = 0
  while i < result.length
    if result[i][0].downcase.chars.sort.join == word.downcase.chars.sort.join
      result[i] += [word]
      return result
    end
    i += 1
  end
  result += [[word]]
  return result
end
