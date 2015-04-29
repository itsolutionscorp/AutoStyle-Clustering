class Anagram

  def initialize(word, comparator = SortedCharacterList)
    @comparator = comparator
    @intial_word = comparator.new(word)
  end

  def match(possible_anagrams)
    possible_anagrams.select { |possible_anagram| anagram?(possible_anagram) }
  end

  private

  attr_reader :comparator, :intial_word

  def anagram?(possible_anagram)
    intial_word == comparator.new(possible_anagram)
  end
end

class SortedCharacterList
  include Comparable

  def initialize(word_to_sort)
    @word_to_sort = word_to_sort
  end

  def <=>(other)
    characters <=> other.characters
  end

  def characters
    word_to_sort.downcase.chars.sort
  end

  private

  attr_reader :word_to_sort
end

class CharacterHistogram
  include Comparable

  def initialize(word_to_count)
    @word_to_count = word_to_count
  end

  def <=>(other)
    character_count <=> other.character_count
  end

  def character_count
    word_to_count.downcase.chars.each_with_object(Hash.new(0)){|char, counts| counts[char] += 1 }
  end

  private

  attr_reader :word_to_count
end
