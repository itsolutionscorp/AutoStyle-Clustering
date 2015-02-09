# input:
#input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  #   <YOUR CODE HERE>
  array = Array.new()
  while (words.length>0) do
    word1 = words.pop()
    test = false
    array.each{ |group|
      if (anagrams(word1, group[0]))
        test = true
        group.push(word1)
      end
    }
    unless (test)
      anas = Array.new()
      anas.push(word1)
      array.push(anas)
    end
  end
  return array
end

def anagrams(word1, word2)
  s1 = word1.downcase.chars.sort.join
  s2 = word2.downcase.chars.sort.join
  if (s1==s2)
    return true
  end
  return false
end

#puts combine_anagrams(input).inspect
