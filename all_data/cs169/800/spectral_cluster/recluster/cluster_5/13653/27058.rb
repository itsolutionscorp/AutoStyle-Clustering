def combine_anagrams(words)
    anagram_groups = []
    words.each { |word|
      pp word
      if anagram_groups.length == 0
        anagram_groups.push([word])
        next
      end

      is_word_added = false
      anagram_groups.each { |group|
        if group.is_a?(Array) && group.first.length == word.length
          if group.first.downcase.chars.sort == word.downcase.chars.sort
            group.push word
            is_word_added = true
            break
          end
        end
      }

      unless is_word_added
        anagram_groups.push([word])
      end
    }

  pp anagram_groups
end