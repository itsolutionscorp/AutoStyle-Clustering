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
    not_the_same?(suspect) && same_breakdown?(suspect)
  end

  def not_the_same? suspect
    suspect != word
  end

  def same_breakdown? suspect
    breakdown(word) == breakdown(suspect)
  end

  def breakdown text
    text.chars.group_by { |char| char }
  end
end
