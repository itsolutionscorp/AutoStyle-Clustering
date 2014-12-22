# input: 
words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter


def combine_anagrams(words)
  #   <YOUR CODE HERE>
  out = []
  for word in words
    in_output = false
    for o in out
      if is_anagram(o[0], word)
        o.push(word)
        in_output = true
      end
    end
    if not in_output
      out.push([word])
    end
  end
  return out
end

def is_anagram(w1, w2)
  return w1.downcase.chars.sort.join == w2.downcase.chars.sort.join
end

puts combine_anagrams(words)
