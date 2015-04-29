class Year < Struct.new(:number)
  def leap?
    divisible_by_four? &&
      (!divisible_by_one_hudred? || divisible_by_four_hundred?)
  end

  def divisible_by_four?
    number % 4 == 0
  end

  def divisible_by_one_hudred?
    number % 100 == 0
  end

  def divisible_by_four_hundred?
    number % 400 == 0
  end
end

class Fixnum
  def leap_year?
    Year.new(self).leap?
  end
end
