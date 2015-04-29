class Year
  def self.leap? year
    divisible_by = -> number { (year % number).zero?}
    divisible_by[4] and !divisible_by[100] or divisible_by[400]
  end
end
