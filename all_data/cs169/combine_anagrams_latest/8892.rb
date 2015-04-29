# HW1-3

def combine_anagrams(words)
  outputArray = []
  words.each do |word|
    # for each word, sort it alpha (downcase) and see if it exists in our output array
    foundInOutputArray = false
    outputArray.each do |anagramArray|
      if (anagramArray[0] == word.downcase.chars.sort.join)
        anagramArray << word
        foundInOutputArray = true
        break
      end
    end
    if (!foundInOutputArray)
      # haven't seen word or anagrams for word yet, so add it
      newarray = [word.downcase.chars.sort.join, word]
      outputArray << newarray
    end
  end
  # finally, clean up outputArray by removing elt 0 (our temp cached sorted word)
  outputArray.each do |anagramArray|
    anagramArray.delete_at(0)
  end  
#  p outputArray
  return outputArray
end

#combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])