class Hamming
  attr_reader :str1, :str2

  def self.compute(str1, str2)
    new(str1, str2).compute
  end

  def initialize(str1, str2)
    length_to_check = [str1.length, str2.length].min

    @str1 = str1[0, length_to_check]
    @str2 = str2[0, length_to_check]
  end

  def compute
    char_pairs.inject(0) do |distance, char_pair|
      distance = distance + 1 if chars_are_different?(char_pair)
      distance
    end
  end

  private

  def char_pairs
    str1.chars.zip(str2.chars)
  end

  def chars_are_different?(char_pair)
    char_pair.first != char_pair.last
  end
end
