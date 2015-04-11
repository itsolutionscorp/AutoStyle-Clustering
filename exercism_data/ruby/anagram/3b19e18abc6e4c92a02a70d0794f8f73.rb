class Anagram
  def initialize(target)
    @target = normalize_word(target)
  end

  def match(candidates)
    candidates.select do |candidate|
      anagram?(candidate)
    end
  end

  private

  def normalize_word(word)
    word.split("").sort.join("")
  end

  def anagram?(candidate)
    @target == normalize_word(candidate)
  end
end
