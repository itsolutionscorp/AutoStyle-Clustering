class Anagram
  attr_reader :original_downcased

  def initialize(original)
    @original_downcased = original.downcase
  end

  def match(others)
    others.select { |word| is_anagram?(word.downcase) }
  end

protected
  def is_anagram?(other)
    same_letters?(other) && !original_downcased.eql?(other)
  end

  def same_letters?(other)
    other.split('').sort == original_downcased.split('').sort
  end
end
