def are_anagrams?(word1, word2)
  # not using String.chars since not supported in old Ruby versions
  return (word1.downcase.split(//).sort.to_s == word2.downcase.split(//).sort.to_s)
end

# return true if word is an anagram of the 
# words in array, false otherwise
def contains_anagram?(array, word) 
  array.each { |elt|
    if(are_anagrams?(elt, word))
      return true
    end
  }

  false
end

def handle_anagrams(array, word)
  array.each { |elt|
    if(contains_anagram?(elt, word))
      elt.push(word)
      return
    end
  }
  
  # no anagram found, add new array
  array.push([word])
end

def combine_anagrams(words)
  anagrams = []

  words.each {|word|
    anagram_found = false
    anagrams.each { |elt|
      if(contains_anagram?(elt, word))
        elt.push(word)
        anagram_found = true
        break
      end
    }
  
    # no anagram found, add new array
    if(!anagram_found)
      anagrams.push([word])
    end
  }

  return anagrams
end