class Anagram
  def initialize(word)
    @canonical = canonicalize(word)
  end

  def match(candidates)
    candidates.select { |c| canonicalize(c) == @canonical }
  end

  private

  def canonicalize(word)
    word.downcase.split(//).sort.join
  end
end
