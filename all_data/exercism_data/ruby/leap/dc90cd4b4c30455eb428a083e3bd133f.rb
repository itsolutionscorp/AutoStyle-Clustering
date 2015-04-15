class Year

  def self.leap?(year)
    year = self.new(year)
    year.leap?
  end

  def initialize(year)
    @year = year
  end

  def leap?
    divisible_by_four_hundred? || ( divisible_by_four? && ! divisible_by_one_hundred? )
  end

  def divisible_by_four?
    @year.modulo(4) == 0
  end

  def divisible_by_one_hundred?
    @year.modulo(100) == 0
  end

  def divisible_by_four_hundred?
    @year.modulo(400) == 0
  end

end
