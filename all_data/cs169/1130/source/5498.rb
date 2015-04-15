 input= ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# # => output: [["cars", "racs", "scar"], ["four"], ["for"],
# ["potatoes"], ["creams", "scream"]]
# # HINT: you can quickly tell if two words are anagrams by sorting their
# # letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)
  hash = Hash.new
  words.each {|w|
    if(hash[w.downcase.split("").sort().join] == nil)
     hash[w.downcase.split("").sort().join] = []  
    end
    hash[w.downcase.split("").sort().join] << w
  }
  rList = []
  hash.each_value{|v|
    rList << v
  }
  return rList

end

