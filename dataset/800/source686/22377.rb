#/usr/bin/env ruby

def combine_anagrams(words)
  anagrams_hash=Hash.new([])

  words.each do|word|
    key=word.downcase.chars.sort.join
    anagrams_hash[key]=anagrams_hash[key] + [word]
  end
  final=anagrams_hash.to_a
  result=[]
  final.each do|pair|
    result = result + [pair[1]]
  end
  return result
end
