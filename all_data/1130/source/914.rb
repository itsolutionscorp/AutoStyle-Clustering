


def combine_anagrams(words)
  hash_list = {}

  words.each do |word|
    k = word.downcase.chars.sort.join
    if hash_list[k]
       hash_list[k] << word
    else
       hash_list[k] = [word]
    end
  end

  return hash_list.values
end

#combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
