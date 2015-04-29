class Anagram
  def initialize(word)
    @word       = word
    @word_chars = chars(word)
  end

  def match(candidates)
    candidates.select { |c| anagram?(c) }
  end

  private

  def anagram?(candidate)
    distinct?(candidate) && same_chars?(candidate)
  end

  def distinct?(candidate)
    candidate.casecmp(@word) != 0
  end

  def same_chars?(candidate)
    chars(candidate) == @word_chars
  end

  def chars(s)
    s.downcase.chars.sort
  end
end
