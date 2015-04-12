class Anagram

  def initialize(matcher)
    @matcher = matcher
  end

  def match(entries)
    entries.select do |entry|
      matching_letters?(entry) && !matching_word?(entry)
    end
  end

private
  def matching_letters?(word)
    normalize_word(word) == normalize_word(@matcher)
  end

  def matching_word?(word)
    word.downcase == @matcher.downcase
  end

  def normalize_word(word)
    word.downcase.chars.sort
  end

end