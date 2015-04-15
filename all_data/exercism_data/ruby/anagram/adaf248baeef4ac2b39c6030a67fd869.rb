class Anagram
  def initialize(word)
    @word = word
    @canonical = canonical(word)
  end

  def match(candidates)
    candidates.select { |word| match?(word) }
  end

  private

  def match?(word)
    different?(word) && anagram?(word)
  end

  def different?(word)
    @word.casecmp(word).nonzero?
  end

  def anagram?(word)
    @canonical == canonical(word)
  end

  def canonical(word)
    word.downcase.chars.sort.join
  end
end
