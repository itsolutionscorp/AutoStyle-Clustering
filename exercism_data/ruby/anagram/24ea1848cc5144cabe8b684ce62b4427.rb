class Anagram
  def initialize(word)
    @word = normalized(word)
  end

  def match(candidates)
    candidates.select { |candidate| valid_anagram? candidate }
  end

  private

  def word
    @word
  end

  def normalized(subject)
    subject.downcase
  end

  def valid_anagram?(candidate)
    candidate = normalized(candidate)

    !same_word?(candidate) && same_length?(candidate) && same_letters?(candidate)
  end

  def same_word?(candidate)
    candidate == word
  end

  def same_length?(candidate)
    candidate.length == word.length
  end

  def same_letters?(candidate)
    word.chars.all? do |letter|
      candidate.count(letter) == word.count(letter)
    end
  end
end
