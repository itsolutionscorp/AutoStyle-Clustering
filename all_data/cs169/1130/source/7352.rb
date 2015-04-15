def combine_anagrams(words)
  tmp_words = Array.new
  words.each do |word|
    tmp_words << word.downcase
  end
  
  anagrams = Array.new
  used = Array.new(words.length, false)
  
  tmp_words.each_with_index { |word, i|
    if !used[i]
      arr = [i]
      used[i] = true
      tmp_words.each_with_index { |w, j|
        if !used[j] && word.chars.sort.join == w.chars.sort.join
          arr << j
          used[j] = true
        end
      }
      tmp = Array.new
      arr.each { |i|
        tmp << words[i]
      }
      anagrams << tmp
    end
  }
  anagrams
end

words = ['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream']

combine_anagrams(words)
