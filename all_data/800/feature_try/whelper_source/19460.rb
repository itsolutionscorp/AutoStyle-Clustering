def combine(first, words)
  b = first.downcase.chars.sort.join
  result = [first]
  cresult = []
  words.each do |word|
    if (word.downcase.chars.sort.join == b) then
      result = (result + [word])
    else
      cresult = (cresult + [word])
    end
  end
  [[result], cresult]
end

def combine_anagrams(words)
  if (words.length == 0) then
    []
  else
    if (words.length == 1) then
      [words]
    else
      if (words.length == 2) then
        c = combine(words[0], [words[1]])[0]
      else
        result = combine(words[0], words[(1..-1)])
        (result[0] + combine_anagrams(result[1]))
      end
    end
  end
end

