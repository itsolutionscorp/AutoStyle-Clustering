class Year
  def self.leap?( year )
    new(year).leap?
  end

  def initialize( year )
    @year = year
  end

  def leap?
    return true if divisible_by? 400

    return false if divisible_by? 100

    divisible_by? 4
  end

  private

  def divisible_by?( num )
    @year % num == 0
  end
end
