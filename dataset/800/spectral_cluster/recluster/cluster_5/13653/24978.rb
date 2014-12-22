#hw1p3
def combine_anagrams(words)
  awords = words.collect{|word| [word.downcase.chars.sort.join,word]}.sort
  asortedu = words.collect{|word| word.downcase.chars.sort.join}.uniq
  
  finished = []
  asortedu.each do |uword|
    set = []
    awords.each do |word|
      set.push(word[1]) if word[0] == uword
    end
    finished.push(set)
  end
  return finished
end