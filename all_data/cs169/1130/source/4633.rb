
def combine_anagrams(words)
  word_hash = {}
  words.each { |word|
    lower_word = word.downcase
    puts lower_word
    sorted_word = lower_word.split('').sort.join
    puts sorted_word
    if word_hash.include?(sorted_word)
      word_hash[sorted_word] << word
    else
      word_hash[sorted_word] = [word]
    end
  }
  puts word_hash.values
  return word_hash.values
end

combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
# #  => output:  [["cars", "racs", "scar"], ["four"], ["for"], 
# ["potatoes"], ["creams", "scream"]]
