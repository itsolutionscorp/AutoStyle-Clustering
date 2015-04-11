class Hamming
  def self.compute(string_a, string_b)
    new(string_a, string_b).number_of_differences
  end

  def initialize(string_a, string_b)
    @string_a, @string_b = string_a, string_b
  end

  def number_of_differences
    differences.size
  end

  private

  def differences
    strings_pairwise_select do |a, b|
      different?(a, b)
    end
  end

  def strings_pairwise_select(&selector)
    @string_a.chars.zip(@string_b.chars).select do |a, b|
      selector.call(a, b)
    end
  end

  def different?(a, b)
    a && b && a != b
  end
end
