def combine_anagrams(words)
  ans = []
  wordlist = words.dup
  wordlist.each do |word|
    b = wordlist.select { |x| anagram?(word, x)}
    ans.push(b)
  end
  ans.uniq!
  return ans
end

def anagram?(word1, word2)
  return word1.downcase.split('').sort == word2.downcase.split('').sort
end

#print combine_anagrams([])
#print combine_anagrams(['cars', 'for', 'potatoes', 'four', 'creams'] )