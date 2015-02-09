def combine_anagrams(words)
  hash = words.inject({}) do |hash,word|
    #if(!hash.has_key?(word.downcase.chars.sort.join))
    #  hash[word.downcase.chars.sort.join] = Array.new
    #end
    hash[word.downcase.chars.sort.join] ||= Array.new
    hash[word.downcase.chars.sort.join].push word    
    hash
  end
  hash.values  
end


puts combine_anagrams(["dog","god","God","bat","oDg","the","tab"])
