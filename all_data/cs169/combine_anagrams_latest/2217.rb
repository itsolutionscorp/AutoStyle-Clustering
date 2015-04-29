#!/usr/bin/env ruby

class String
  def anagram?(word)
    downcase.chars.sort.to_s == word.downcase.chars.sort.to_s
  end
end

def combine_anagrams(words)
  return [words] if words.length == 1
  return words if words.length < 1
  partitions = words.partition { |word| word.anagram?(words[0]) }
  return [partitions[0]] + combine_anagrams(partitions[1])
end
