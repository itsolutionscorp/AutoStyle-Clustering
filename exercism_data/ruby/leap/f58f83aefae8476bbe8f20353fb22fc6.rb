module Year
  def self.leap?(year)
    divisible_by?(year, 4) && !edge_case(year)
  end

  def self.divisible_by?(year, num)
    year % num == 0
  end

  def self.edge_case(year)
    divisible_by?(year, 100) && !divisible_by?(year, 400)
  end
end
