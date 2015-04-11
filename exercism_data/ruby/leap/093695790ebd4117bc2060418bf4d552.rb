class Year
  attr_reader :number

  def self.leap?(input)
    Year.new(input).leap?
  end

  def initialize(number)
    @number = number
  end

  def leap?
    fourth_year? && (!century? || fourth_century?)
  end

  def fourth_year?
    number % 4 == 0
  end

  def century?
    number % 100 == 0
  end

  def fourth_century?
    number % 400 == 0
  end
end
