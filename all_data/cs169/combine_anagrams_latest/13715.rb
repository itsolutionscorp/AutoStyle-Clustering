
def is_anagram(word1, word2)
  str1 = word1.downcase().chars.sort.join('')
  str2 = word2.downcase().chars.sort.join('')
  return str1 == str2
end


def combine_anagrams(words) 
  anagrams = []
  words.each{|word|
    existed = false
    anagrams.each { |anagram|
      if is_anagram(anagram[0], word)
        anagram.push(word)
        existed = true
        break
      end
    }
    if not existed
      anagrams.push([word])
    end
  }
  return anagrams
end



words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
result =  combine_anagrams(words)
puts result.inspect
