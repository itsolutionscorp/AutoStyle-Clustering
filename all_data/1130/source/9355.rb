def combine_anagrams(words)
  ana = Array.new
  words.each { |word|
    word_sorted = word.downcase.chars.sort.join
    found = false
    temp = Array.new
    ana.each { |a|
      if a[0].downcase.chars.sort.join == word_sorted
        a = a + [ word ]
        found = true
      end
      temp = temp + [ a ]
    }
    ana = temp
    if !found
      w = [ word ]
      ana = ana + [ w ]
    end
  }
  return ana
end
