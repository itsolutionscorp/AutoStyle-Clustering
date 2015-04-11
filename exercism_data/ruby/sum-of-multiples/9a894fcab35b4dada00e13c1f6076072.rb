class SumOfMultiples
  attr_reader :digit

  def initialize(*digit)
    @digit = digit
  end

  def self.to(final_number) 
    (0...final_number).select { |integer| multiples?(integer) }.reduce(:+)
  end

  def to(final_number) 
    (1...final_number).select { |integer| multiples_of_digits?(integer) }.reduce(:+)
  end

  def self.multiples?(integer)
    integer % 3 == 0  || 
    integer % 5 == 0
  end  

  def multiples_of_digits?(number)
    digit.any? { |digit| number % digit == 0 }
  end
end
