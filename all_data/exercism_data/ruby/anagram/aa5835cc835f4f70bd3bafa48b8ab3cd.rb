class Anagram

  def initialize(matcher)
    @matcher = matcher
  end

  def match(entries)
    entries.select do |entry|
      same_letters?(entry, @matcher) unless same_word?(entry, @matcher)
    end
  end

private
  def same_letters?(first_word, second_word)
    normalize_word(first_word) == normalize_word(second_word)
  end

  def same_word?(first_word, second_word)
    first_word.downcase == second_word.downcase
  end

  def normalize_word(word)
    word.downcase.chars.sort
  end

end
