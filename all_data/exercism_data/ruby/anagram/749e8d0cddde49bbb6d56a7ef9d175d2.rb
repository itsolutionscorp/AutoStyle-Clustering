class Anagram
  def initialize word
    @word = word.downcase
  end

  def match words
    words.select do |suspect|
      is_anagram? suspect.downcase
    end
  end

  private
  attr_reader :word

  def is_anagram? suspect
    different_word?(suspect) && same_breakdown?(suspect)
  end

  def different_word? suspect
    suspect != word
  end

  def same_breakdown? suspect
    breakdown(word) == breakdown(suspect)
  end

  def breakdown text
    text.chars.sort
  end
end
