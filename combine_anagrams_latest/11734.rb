def anapair(words)
  result = []
  match = 0
  words.each {
    |x|
    words.each {
      |y|
      if (x.downcase.chars.sort.join  == y.downcase.chars.sort.join) then
        result.push y
      end
    }
    break
  }

  result.each {
    |w| words.delete(w)
  }

  result
end

def combine_anagrams(words)
  res=[]
  while words.size > 0 do
    res.push(anapair(words))
  end

  res
end
