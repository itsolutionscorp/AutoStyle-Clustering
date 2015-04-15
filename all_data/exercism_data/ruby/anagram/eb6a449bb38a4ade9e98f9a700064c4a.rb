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
    !original_downcased.eql?(other) && same_letters?(other)
  end

  def same_letters?(other)
    other.split('').sort == sorted_original_letters
  end

  def sorted_original_letters
    @sorted_original_letters ||= original_downcased.split('').sort
  end
end
