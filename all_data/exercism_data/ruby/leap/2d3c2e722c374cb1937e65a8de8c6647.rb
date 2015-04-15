class Year
  def self.leap? year
    evenly_divisible(year, 4) &&
      (!evenly_divisible(year, 100) ||
       (evenly_divisible(year, 100) &&
        evenly_divisible(year, 400)))
  end

  private
  def self.evenly_divisible year, number
    year % number == 0
  end
end
