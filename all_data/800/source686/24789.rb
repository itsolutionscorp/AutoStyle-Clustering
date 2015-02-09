#!/usr/bin/env ruby
#puts "HW1 part 3: anagrams (done)"

# Example:
# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]

def combine_anagrams(words)
  h = {}
  words.each { |x|
    key = x.downcase.split('').sort.join
    if (h[key]) then
      h[key].push(x)
    else
      h[key] = [x]
    end
  }
  return h.values
end

def mytest1
  p combine_anagrams(['cars','for','potatoes','racs','four','scar','creams','scream'])
end

