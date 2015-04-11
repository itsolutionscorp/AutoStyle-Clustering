class Year
  def self.leap? year
    evenly_divisible(year, 4) &&
      (!evenly_divisible(year, 100) ||
       (evenly_divisible(year, 100) &&
        evenly_divisible(year, 400)))
  end

  def self.evenly_divisible year, number
    year % number == 0
  end

  private_class_method :evenly_divisible
end
