def combine_anagrams(words)
  toRet = []
  wordSet = false
  words.each do |word|
    wordSet = false
    toRet.each do |l|
      if key(l[0]) == key(word)
        l.push(word)
        wordSet = true
      end
    end
    if not wordSet
      a = []
      a.push word
      toRet.push(a)
    end
    wordSet = false
  end
  return toRet
end
  

def key(a)
  return a.to_s.downcase.chars.sort.join
end




