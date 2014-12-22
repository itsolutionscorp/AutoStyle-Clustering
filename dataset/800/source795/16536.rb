
class String
  def is_anagram(word)
    return self.downcase.chars.sort.join == word.downcase.chars.sort.join
  end
end

def combine_anagrams(words)
  result = []
  
  until words.empty?
    current = words.pop
    temp = []
    add = [current]
    until words.empty?
      nextw = words.pop
      if current.is_anagram(nextw)
        add.push(nextw)
      else
        temp.push(nextw)
      end
     end
      result.push(add)
      words = temp
   end  
   return result
end



# input:
#print combine_anagrams ( ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] )
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
