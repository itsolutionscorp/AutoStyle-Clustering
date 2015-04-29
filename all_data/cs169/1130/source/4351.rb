#3
def combine_anagrams(words)
  new_array = words.map do |word|
    word.downcase.chars.sort.join
  end
  return combine_anagrams_helper(words, new_array)
end

def combine_anagrams_helper(words, anagrams)
  sorted_list = []
  while !words.empty?
    anagrams.each do |word|
      index = anagrams.index(word)
      current_word_list = [words[index]]
      # Remove word we're looking at from both words and anagrams list
      remove_word_from(words, anagrams, index)
      # Find any matching anagrams in anagrams list
      matching_index = anagrams.index(word)
        while matching_index
          current_word_list.push(words[matching_index]) # add matching anagram's word to list
          remove_word_from(words, anagrams, matching_index)
          matching_index = anagrams.index(word)
        end
      sorted_list.push(current_word_list)
    end
  end
  return sorted_list
end

def remove_word_from(words, anagrams, index)
  words.delete_at(index)
  anagrams.delete_at(index)
end