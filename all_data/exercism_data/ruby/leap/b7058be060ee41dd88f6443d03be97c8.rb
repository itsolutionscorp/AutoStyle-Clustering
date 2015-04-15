class Year
  def self.leap?(year)
    divisible_by = ->(dividend) { year % dividend == 0 }

    return true  if divisible_by[400]
    return false if divisible_by[100]

    divisible_by[4]
  end
end
