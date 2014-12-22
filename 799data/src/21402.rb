def anagram(string1, string2)
  temp1 = string1.downcase.chars.sort.join
  temp2 = string2.downcase.chars.sort.join

  return temp1 == temp2
end

#puts anagram("rats","tars")

def is_match(group, word)
  group.each do |match|
    if(anagram(match, word))
      return true
    end
  end
  return false
end

def combine_anagrams(words)
  result = Array.new

  #result[0] = Array.new

  words.each do |word|
    isMatch = false
    result.each do |group|
      if(is_match(group, word))
        group << word
        isMatch = true
        break
      end
    end
    if(!isMatch)
      result << [word]
    end
  end
  return result
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).to_s



