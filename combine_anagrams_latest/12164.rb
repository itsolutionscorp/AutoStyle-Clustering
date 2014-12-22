def combine_anagrams(words)
  anagrams = {}
  words.each { |w|
    word = w.downcase.split('').sort.join('')
    if anagrams[word]
      anagrams[word] += [ w ]
    else
      anagrams[word] = [ w ]
    end
  }
  anagrams.values
end
