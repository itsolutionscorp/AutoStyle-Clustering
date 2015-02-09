# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams','scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"],["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  poshash = Hash.new
  owords = Array.new
  words.each do |word|
    tmpword=word.downcase
    code=tmpword.chars.sort.join
    if(!poshash.has_key?(code))
      #new word
      poshash[code]=owords.length
      owords[poshash[code]] = Array.new
    end
    
    wordpos=owords[poshash[code]].length
    owords[poshash[code]][wordpos]=word    
  end
  return owords

end

#puts combine_anagrams(['cars', 'for', 'potatoes', 'Racs', 'four','scar', 'creams','scream']).inspect
