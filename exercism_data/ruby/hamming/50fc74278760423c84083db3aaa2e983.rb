class Hamming
  def self.compute(first, second)
    new(first, second).find_differences
  end

  attr_reader :first, :second, :differences

  def initialize(first, second)
    @first = first.chars
    @second = second.chars
    @differences = 0
  end

  def find_differences
    first.each_with_index do |_, index|
      if second[index]
        @differences += 1 if first[index] != second[index]
      end
    end
    differences
  end
end
