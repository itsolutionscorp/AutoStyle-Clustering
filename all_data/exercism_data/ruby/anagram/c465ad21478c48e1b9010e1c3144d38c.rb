class Word

  attr_reader :word

  def initialize(word)
    @word = word.downcase
  end

  def ==(candidate)
    @word == candidate.word
  end

  def same_letters?(candidate)
    sorted_letters == candidate.sorted_letters
  end

  def sorted_letters
    @word.chars.sort
  end

end

class Anagram < Word

  def match(candidates)
    candidates.find_all { |candidate| anagram?(candidate) }
  end

  private 

  def anagram?(candidate)
    candidate = Word.new(candidate)
    same_letters?(candidate) && self != candidate
  end
end
