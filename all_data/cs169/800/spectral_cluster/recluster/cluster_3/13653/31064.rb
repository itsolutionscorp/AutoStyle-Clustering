# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  anagrams = [] 
  indexes = Hash.new()
  fmtd_words = words.collect { |w|
    w.split('').sort().join('').downcase()
  }
  fmtd_words.each_index { |i|
    word = fmtd_words[i]
    if indexes[word]
      indexes[word] << i 
    else
      indexes[word] = [i]
    end
    print indexes[fmtd_words[i]], fmtd_words[i], i, "\n"
  }
  print indexes, "\n"
  indexes.each { | _, value|
    anagrams << value.collect { |i|
      words[i]
    }
  }
  return anagrams
end

#print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
