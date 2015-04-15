class Year < Struct.new(:value)
  def self.leap?(year)
    Year.new(year).leap?
  end

  def leap?
    divisible_by(4) && (!divisible_by(100) || divisible_by(400))
  end

  private

  def divisible_by(number)
    value % number == 0 
  end
end
