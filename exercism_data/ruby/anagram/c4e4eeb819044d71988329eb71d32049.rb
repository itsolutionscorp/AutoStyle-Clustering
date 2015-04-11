class Anagram
  attr_reader :word

  def initialize(word)
    @word = word.downcase
  end

  def match(candidates)
    candidates.find_all { |candidate| anagram?(candidate) }
  end

  def anagram?(candidate)
    candidate = Anagram.new(candidate.to_s)
    same_characters?(candidate) && different_word?(candidate)
  end

  def characters
    @characters ||= word.chars.sort.join
  end

  private

  def different_word?(other)
    word != other.word
  end

  def same_characters?(other)
    characters == other.characters
  end
end
