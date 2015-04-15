#!/usr/bin/env ruby

def combine_anagrams(words)
  result =  Array.new 
  anag_ar = Array.new
  done_words = Array.new

  for i in words
    if done_words.include?(i)
       next 
    end
    inertm_ar = Array.new
    inertm_ar.push(i)
    ind = words.index(i) + 1
    comp_ar = words[ind,words.length]
    wd_split = i.downcase.split('')
    sort_wd = wd_split.sort

 
    for j in comp_ar 
       comp_split = j.downcase.split('')
       n_wd_split = comp_split.sort
      if  n_wd_split == sort_wd
        inertm_ar.push(j)
        done_words.push(j)        
      end
      
    end
    result.push(inertm_ar)
  end  
  return   result
  #print result
end

#combine_anagrams(['Cars','A','a','A','a', 'for', 'potatoes', 'racs', 'four','scAr', 'creams', 'scream'] )
#combine_anagrams(['Cars','Ab cdes','ba dsce','bA cesd','a'] )
#combine_anagrams(['abc','ab','bA','A','a'])
