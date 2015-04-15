def palindrome?(string)
  string = string.downcase.gsub(/\W/, '')
  if string == string.reverse
    return true
  end
  return false
end
def count_words(string)
  string_hash = {};
  string_array = string.downcase.gsub(/\W/, ' ').split(' ')
  string_array.each do |word|
    if string_hash.has_key?(word)
      string_hash[word] = string_hash[word] + 1
    else
      string_hash[word] = 1
    end
  end  
  return string_hash
end

def combine_anagrams(words)
  words_hash = {};
  results = [];
  words.each do |word|
    key = word.downcase.chars.sort.join
    if words_hash.has_key?(key)
      words_hash[key].push(word)
    else
      words_hash[key] = [word]
    end
  end  
  
  words_hash.each do |key, value|
    results.push(value)
  end
  return results
end

combine_anagrams(['cars', 'For', 'pOTatoes', 'RAcs', 'four', 'scar', 'creams', 'scream'])
