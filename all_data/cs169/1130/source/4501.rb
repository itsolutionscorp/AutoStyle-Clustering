def combine_anagrams(words)
  hash = {}
  anagrams = []

  words.each { |word|

      value = word.downcase.each_char.sort.join

      if (hash[value]==nil)
        hash[value] = Array.new
      end

     hash[value].push word
  }

  hash.each_value { |value|
    anagrams.push value
  }

  return anagrams
end