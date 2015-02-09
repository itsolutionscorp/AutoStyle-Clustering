
def combine_anagrams(words)

  anagram_groups = []

  while (words.length > 0)
    cur_word =   words.delete_at(0)
    cur_group = [cur_word]
    words.each do |word|
      if word.downcase.split('').sort.join == cur_word.downcase.split('').sort.join
         cur_group << word
         words.delete(word)
      end
    end
    anagram_groups << cur_group
  end
  return anagram_groups
end