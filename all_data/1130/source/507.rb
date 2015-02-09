#!/usr/local/bin/env ruby

# 05/26/2012, saas coursera





def combine_anagrams(words)

  $debug=true

  # input: words, is an array of strings
  # output: array of (groups of anagram strings)

  # input:  ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
  # => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]

  # HINT: you can quickly tell if two words are anagrams by sorting their
  # letters, keeping in mind that upper vs lowercase doesn't matter

  if words==nil then
    return []
  elsif words==[] then
    return []
  end
    

  h=Hash.new
  a=[]
  words.each{ |origWord|
    # make has to canonical value
    # keys are original words
    # vals are sorted, downcased words
    sortedOrigWord=[]
    origWord.each_char{ |ch|
      sortedOrigWord.concat([ch.downcase])
    }
    sortedOrigWord=sortedOrigWord.sort
    # recombine sorted chars into string
    newStr=""
    sortedOrigWord.each{ |ch_str|
      newStr+=ch_str
    }
    h[origWord]=newStr
  }
  if $debug then
    puts h
  end    

  hRes=Hash.new
  words.each{ |word|
    if hRes[h[word]]==nil then
      hRes[h[word]]=[word]
    else
      hRes[h[word]]=hRes[h[word]].concat([word])
    end     

  }
  ret=hRes.values.to_s
  if $debug then
    puts ret
  end
  # ret, this looks right but fails, string instead of array?
  ret=hRes.values
  return ret
end # combine_anagrams

# res=combine_anagrams([])
# puts(res)
 res=combine_anagrams(['r','s','t'])
# puts(res)
