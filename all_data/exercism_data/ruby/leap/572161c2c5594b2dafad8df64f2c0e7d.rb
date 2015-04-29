class Year
  def self.leap?(year)
    evenly_divisible_by(year, 4) and !evenly_divisible_by(year, 100) or evenly_divisible_by(year, 400)
  end

  def self.evenly_divisible_by(year, number)
    year % number == 0
  end
end
