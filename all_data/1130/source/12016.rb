#!/usr/local/bin/ruby

def combine_anagrams(words)
  res = Hash.new

  words.each { |w|
    k = w.downcase.chars.sort.join
    if !res[k] then
      # new anagram
      res[k] = Array.new
    end

    # add to list
    res[k].push(w)
  }

  ret = Array.new
  res.each { |k, v|
    ret.push(v)
  }

  return ret
end

#l = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

#p combine_anagrams(l)

#l = ['cars', 'for', 'CarS', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#p combine_anagrams(l)

#p combine_anagrams(['a', 'A', 'b', 'B'])
