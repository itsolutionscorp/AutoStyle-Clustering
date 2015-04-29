class Anagram
  def initialize(word)
    @normalized_word = normalize word
  end

  def match(candidates)
    candidates.select do |candidate|
      fit?(candidate)
    end
  end

  private
  def normalize word
    word.chars.sort
  end

  def fit?(candidate)
    @normalized_word == normalize(candidate)
  end
end
