class Anagram
  def initialize(word)
    @word = word
  end

  def match(potential_matches)
    potential_matches.select do |potential_match|
      WordPair.new(@word, potential_match).anagram?
    end
  end
end

class WordPair
  def initialize(first, second)
    @first, @second = first.downcase, second.downcase
  end

  def anagram?
    same_letters? && not_identical?
  end

  private
  def same_letters?
    sorted_letters_for(first) == sorted_letters_for(second)
  end

  def not_identical?
    first != second
  end

  def sorted_letters_for(word)
    word.chars.sort
  end

  def first
    @first
  end

  def second
    @second
  end
end
