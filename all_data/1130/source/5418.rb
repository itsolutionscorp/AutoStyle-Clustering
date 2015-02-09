
# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  hsh=Hash.new
  words.each{|w|
    normW=norm(w)
    if hsh.key?(normW) then 
      value=hsh.fetch(normW)
      value.push(w)
      hsh.store(normW, value)
     else
       value=[w]
    end
    hsh.store(normW,value)
   }
   return hsh.values
# <YOUR CODE HERE>
end

def norm(str)

return   str.downcase.chars.sort.join
end

