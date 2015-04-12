class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(candidates)
    candidates.select { |c| match?(c.downcase) }
  end

  private

  def match?(candidate)
    candidate != @word && has_same_chars?(candidate)
  end

  def has_same_chars?(candidate)
    sorted_chars(candidate) == sorted_chars(@word)
  end

  def sorted_chars(s)
    s.chars.to_a.sort
  end
end