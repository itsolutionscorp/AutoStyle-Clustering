def is_anagram?(word1, word2)
  if word1.downcase.chars.sort.join == word2.downcase.chars.sort.join
    return true
  else
    return false
  end
end

def combine_anagrams(words)
  list = Array.new
  words.each do |word|
    find_anagram = false
    list.each do |item|
      if is_anagram?(item[0], word)
        item.push(word)
        find_anagram = true
      end
    end
    if not find_anagram
      anagrams = Array.new
      anagrams.push(word)
      list.push(anagrams)
    end
  end
  return list
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
