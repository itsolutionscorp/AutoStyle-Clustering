class Anagram
  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.select { |c| anagram?(c) }
  end

  private

  def anagram?(candidate)
    distinct?(candidate) && same_chars?(candidate)
  end

  def distinct?(candidate)
    candidate.downcase != @word.downcase
  end

  def same_chars?(candidate)
    chars(candidate) == chars(@word)
  end

  def chars(s)
    s.downcase.chars.sort
  end
end
