class Year

  def initialize year_number
    @year = year_number
  end

  def leap?
    by_four? && (not_by_one_hundred? || by_four_hundred? )
  end

  def self.leap?(year_number)
    Year.new(year_number).leap?
  end

  private

  def by_four?
    (@year % 4).zero?
  end

  def not_by_one_hundred?
    (@year % 100).nonzero?
  end

  def by_four_hundred?
    (@year % 400).zero?
  end

end
