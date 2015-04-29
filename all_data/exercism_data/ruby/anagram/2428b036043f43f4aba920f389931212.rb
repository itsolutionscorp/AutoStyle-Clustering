class Word
  def initialize(word)
    @word = word
  end

  def ==(candidate)
    @word.downcase == candidate.downcase
  end

  def same_letters?(candidate)
    sorted_letters == candidate.sorted_letters
  end

  def downcase
    @word.downcase
  end

  def sorted_letters
    @word.downcase.chars.sort
  end
end

class Anagram
  attr_reader :word

  def initialize(word)
    @word = Word.new(word)
  end

  def match(candidates)
    candidates.find_all { |candidate| anagram?(candidate) }
  end

  private

  def anagram?(candidate)
    candidate = Word.new(candidate)
    word.same_letters?(candidate) && word != candidate
  end

end
