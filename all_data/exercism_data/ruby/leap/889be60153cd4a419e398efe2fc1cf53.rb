module Year
  def self.leap?(year)
    return true  if year.divisible_by? 400
    return false if year.divisible_by? 100
    year.divisible_by? 4
  end
end

class Fixnum
  def divisible_by?(n)
    self % n == 0 
  end
end
