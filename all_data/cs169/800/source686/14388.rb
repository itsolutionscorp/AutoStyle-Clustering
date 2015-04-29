def combine_anagrams(words)
  words.flatten!
  orig_words = words.dup
  arranged_words = words.map(&:downcase).map{ |w| [w, words.index(w)] }.map{ |w| [w[0].chars.sort.join, w[1]] }.sort{ |a,b| b[0] <=> a[0] }

  anagrams = [] ; i_anagrams = []
  arranged_words.each do |a_word|
    i_anagrams << [ a_word[0] ]
    anagrams << [ orig_words[a_word[1]] ]
    a_length = i_anagrams.length
    if a_length > 1
      if i_anagrams[a_length - 2].uniq == i_anagrams.last
        i_anagrams[a_length - 2] += i_anagrams.delete(i_anagrams.last)
        anagrams[a_length - 2] += anagrams.delete(anagrams.last)
        i_anagrams.last.flatten!
        anagrams.last.flatten!
      end
    end
  end

  anagrams.sort!{ |a,b| orig_words.index(a[0]) <=> orig_words.index(b[0]) }

  return anagrams
end
