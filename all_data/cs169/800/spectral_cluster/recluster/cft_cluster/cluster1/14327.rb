# Credit to https://gist.github.com/1964733 for starting me off

def anagrams?(word_1, word_2)
 word_1.downcase.split('').sort == word_2.downcase.split('').sort
end

def combine_anagrams(words)
  return_array = []

  tainted_indexes = {}

  words.each_with_index do |original_word, idx|

    next if tainted_indexes[idx]

    words_to_group = []

    words[idx..words.length].each_with_index do |word, idx_inner|
      if anagrams?(original_word, word)
        words_to_group << word
        tainted_indexes[idx+idx_inner] = true
      end
    end

    return_array << words_to_group

  end

  return return_array

end
