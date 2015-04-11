class Anagram
  def initialize(word)
    @word = Word.new word
  end

  def match(potential_matches)
    potential_matches.select do |potential_match|
      WordPair.new(word, Word.new(potential_match)).anagram?
    end
  end

  private
  attr_reader :word
end

class Word < String
  def initialize(word)
    super word.downcase
  end

  def sorted_letters
    @_sorted_letters ||= chars.sort
  end
end

class WordPair
  def initialize(first, second)
    @first, @second = first, second
  end

  def anagram?
    same_letters? && not_identical?
  end

  private
  attr_reader :first, :second

  def same_letters?
    first.sorted_letters == second.sorted_letters
  end

  def not_identical?
    first != second
  end
end
