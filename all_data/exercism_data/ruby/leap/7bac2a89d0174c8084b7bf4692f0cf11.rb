class Year
  def self.leap?(year)
    divisible_by = ->(n) { (year % n).zero? }
    divisible_by[400] || divisible_by[4] && !divisible_by[100]
  end
end