class Anagram
  attr_reader :original_downcased

  def initialize(original)
    @original_downcased = original.downcase
  end

  def match(others)
    data = []
    others.each do |other|
      data.push(other) if is_anagram?(other)
    end
    data
  end

protected
  def is_anagram?(other)
    same_letters?(other) && !original_downcased.eql?(other.downcase)
  end

  def same_letters?(other)
    other.downcase.split('').sort == original_downcased.split('').sort
  end
end
