class Anagram

  def initialize(word, sorter = CharacterSorter)
    @word = word.downcase
    @sorter = sorter
  end

  def match(possible_anagrams)
    possible_anagrams.select { |possible_anagram| anagram?(possible_anagram) }
  end

  private

  attr_reader :word, :sorter

  def anagram?(possible_anagram)
    sorter.sort_characters(word) == sorter.sort_characters(possible_anagram)
  end
end

module CharacterSorter
  def self.sort_characters(word_to_sort)
    word_to_sort.chars.sort
  end
end
