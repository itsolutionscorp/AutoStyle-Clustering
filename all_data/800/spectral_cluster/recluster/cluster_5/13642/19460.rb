
def combine(first, words)
  b = first.downcase.chars.sort.join
  result = [first]
  cresult = []

  words.each do |word|
    if word.downcase.chars.sort.join == b
      result = result + [word]
    else
      cresult = cresult + [word]
    end
  end
  [[result], cresult]
end

def combine_anagrams(words)
    if words.length == 0
    []
  elsif words.length == 1
    [words]
  elsif words.length == 2
    c = combine(words[0], [words[1]])[0]

  else
    result = combine(words[0], words[1..-1])
    result[0] + combine_anagrams(result[1])
  end
end
