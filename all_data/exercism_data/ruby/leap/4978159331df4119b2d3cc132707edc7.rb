class Year
  def initialize year=nil
    @year = year
  end

  def self
    @year
  end

  def self.leap? year
    year = Year.new(year)

    unless year.century?
      year.divisible_by_four?
    else
      year.divisible_by_four_hundred?
    end
  end

  def century?
    @year % 100 == 0
  end

  def divisible_by_four?
    @year % 4 == 0
  end

  def divisible_by_four_hundred?
    @year % 400 ==0
  end
end
