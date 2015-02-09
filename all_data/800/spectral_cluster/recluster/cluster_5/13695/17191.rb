# input:
#words=['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def anagramcmp(w1,w2)
  return w1.chars.sort{|a, b| a.casecmp(b)}.join.casecmp(w2.chars.sort{|a, b| a.casecmp(b)}.join)
end

def combine_anagrams(words)
  #   <YOUR CODE HERE>
  if words.empty? then
    return words
  end

  sorted=words.each.sort{|w1, w2| anagramcmp(w1,w2)}
  anagrams=Array.new
  anagram=sorted[0]

  i=0
  anagrams << Array.new
  anagrams[0]=Array.new

  sorted.each { |w|
    if (anagramcmp(w,anagram)==0) then
      anagrams[i] << w
    else
      i+=1
      anagrams << Array.new
      anagram=w
      anagrams[i]<<w
    end
  }
  return anagrams
end

#require 'pp'
#PP.pp(combine_anagrams(words))
