class Anagram
  attr_reader :word

  def initialize(word)
    @word = word.downcase
  end

  def match(list)
    list.select do |candidate|
       Anagrammer.new(word, candidate).anagram?
    end
  end
end


class Anagrammer
  attr_reader :word, :candidate

  def initialize(word, candidate)
    @word, @candidate = word, candidate
  end

  def anagram?
    !exact_word? && sorted_match?
  end

  private

  def sorted_match?
    word.split('').sort == candidate.split('').sort
  end

  def exact_word?
    word == candidate
  end
end
