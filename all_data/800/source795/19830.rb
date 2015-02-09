# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter

def anagram?(first, second)
  a = first.chars.sort{ |k,l| k.casecmp l }.join
  b = second.chars.sort{ |k,l| k.casecmp l }.join
  return a.casecmp b
end

def anagram_arr?(word, arr)
  out = []
  arr.each do |val|
    if (anagram? word, val) == 0 then
    out << val
    end
  end
  return out
end

def combine_anagrams(words)
  arr = []

  words.each do |word|
    arr << (anagram_arr? word, words)
  end

  return arr.uniq
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'Racs', 'four','scar', 'creams', 'scream']).inspect
