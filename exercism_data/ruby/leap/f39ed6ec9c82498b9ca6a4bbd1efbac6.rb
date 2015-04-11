module Year
  def self.leap? year
    year_is_divisible_by = ->(increment) { year % increment == 0 }

    return true  if year_is_divisible_by.(400)
    return false if year_is_divisible_by.(100)
    return true  if year_is_divisible_by.(  4)
    
    return false  # all other years aren't leap years
  end
end
