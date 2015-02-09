
def combine_anagrams(words)
  if words.length == 1
    return Array[words]
  end
  anagrams = combine_anagrams(words[0..-2])
  last_word = words[-1]
  last_word_belongs_to_a_previous_group = false
  anagrams.each do |group|
    if last_word.downcase.chars.sort == group[0].downcase.chars.sort
      group << last_word
      last_word_belongs_to_a_previous_group = true
    end
  end
  if (last_word_belongs_to_a_previous_group == false)
    anagrams << Array[last_word]
  end
  return anagrams
end


