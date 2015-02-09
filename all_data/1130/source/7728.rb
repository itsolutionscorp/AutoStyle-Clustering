def is_anagram? (word1, word2)
  return word1.downcase.chars.sort.join == word2.downcase.chars.sort.join
end

def combine_anagrams(words)
  twords    = words.dup
  prev_ana  = Array.new
  new_ana   = Array.new
  pwords    = Array.new
  
  words.each  do |w|
    next if pwords.find_index(w)
    twords.each do |test_word|
      next if pwords.find_index(test_word)
     if is_anagram?(w, test_word)
       puts "match"
      prev_ana.push test_word
      pwords.push test_word
     end
    end
    new_ana.push prev_ana.dup
    prev_ana.clear
  end
  return new_ana
end
