# Part 3: anagrams

###############
def combine_anagrams(words)

  anagram_groups = []

  word_pairs = words.map do |word|
    sorted_word = word.
    downcase.
    scan(/./).
    sort.
    join
    
    [sorted_word, word]
  end

  until word_pairs.length == 0
    word_group = []

    delete_word = word_pairs[0][0]

    word_pairs.each_index do |i|
      if word_pairs[i][0] == delete_word
        word_group << word_pairs[i][1]
      end
    end

    word_pairs.delete_if { |word_pair| delete_word == word_pair[0] }

    anagram_groups << word_group
  end

  return anagram_groups
end

#-----------------------------------------------------------------------------------------

words = ['cars', 'for', 'scream', 'potatoes', 'racs', 'four', 'cars', 'scar', 'creams', 'scream']
p combine_anagrams(words)

words = ['a', 'd', 'a', 'c', 'c', 'a', 'b', 'a', 'd', 'a']
p combine_anagrams(words)
