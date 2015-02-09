def combine_anagrams(words)
  staginghash = Hash.new
  words.each { |x| 
    sortedletters = x.downcase
    sortedletters = sort_letters(sortedletters)
    if staginghash.has_key?(sortedletters)
      staginghash[sortedletters] = staginghash[sortedletters].push(x)
    else
      newwordarray = Array.new
      newwordarray.push(x)
      staginghash[sortedletters] = newwordarray
    end
  }
  return staginghash.values
end

def sort_letters(word)
  returnarray=Array.new
  word.each_char { |c|
    returnarray.push(c)
  }
  returnarray = returnarray.sort
  return returnarray.join
end