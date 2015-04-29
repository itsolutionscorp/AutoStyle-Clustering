class Anagram
  def initialize(word)
    @word = normalize word
  end

  def match(candidates)
    candidates.select do |candidate|
      candidate if fit?(candidate)
    end
  end

  private
  def normalize word
    word.chars.sort
  end

  def fit?(candidate)
    @word == normalize(candidate)
  end
end
