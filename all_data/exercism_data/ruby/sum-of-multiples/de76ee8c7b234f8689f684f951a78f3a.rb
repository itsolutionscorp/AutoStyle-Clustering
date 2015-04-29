class SumOfMultiples
  attr_reader :number_array

  def initialize(*numbers)
    @number_array = numbers
  end

  def self.to(last_number)
    (0...last_number).select { |integer| multiple?(integer) }.inject(:+)
  end

  def to(last_number)
    (0...last_number).select { |integer| multiple_of_inputs?(integer) }.inject(:+)
  end

  def self.multiple?(integer)
    integer % 3 == 0 || integer % 5 == 0
  end 

  def multiple_of_inputs?(integer)
    number_array.any? { |number| integer % number == 0 }
  end

end
