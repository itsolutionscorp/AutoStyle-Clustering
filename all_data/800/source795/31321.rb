def group_anagrams(sorted,result_arr)
  
  # get the first word-pair and find all its anagrams
  # store anagram array in result and recurse with the rest of the words
  w=sorted.shift
  anagram_arr=[w[0]]
  rest_arr=Array.new
  sorted.each_index { |i|
    if (w[1] == sorted[i][1]) then
      anagram_arr << sorted[i][0]
    else
      rest_arr << sorted[i]
    end
  }
  # add the anagram_arr into our result array
  result_arr << anagram_arr

  # if there are no more words then we can return
  if rest_arr.empty? then return end

  # we look for the rest of the anagrams
  group_anagrams(rest_arr,result_arr)
end
 
  
 
def combine_anagrams(words)
  if words.empty? then return [] end

  # create an array with 2 element arrays of word and its sorted string 
  sorted=Array.new
  words.each_index { |i|
    letter_array=words[i].upcase.scan(/./)
    letter_array.sort!
    sw=letter_array.to_s
    sorted[i]=[words[i],sw]
  }
  result_arr=[]
  group_anagrams(sorted,result_arr)
  return result_arr
end
