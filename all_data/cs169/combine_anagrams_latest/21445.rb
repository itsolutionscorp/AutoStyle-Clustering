def combine_anagrams(words)
  anagrams = Array.new

  word_added = false

  words.each do |word|
    if anagrams.empty?
      anagrams.push([word])
    else
      anagrams.each do |anagram|
        anagram.each do |anagram_word|
          if word.downcase.chars.sort.join.eql? anagram_word.downcase.chars.sort.join
            anagram.push(word)
            word_added = true
            break
          end
        end
      end
      if !word_added
        anagrams.push([word])
      end
      word_added = false            # reset var
    end
  end

  return anagrams

end
