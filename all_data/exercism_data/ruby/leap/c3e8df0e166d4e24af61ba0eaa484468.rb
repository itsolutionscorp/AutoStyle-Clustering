module Year
  def self.leap?(year)
    factor?(year, 400) || factor?(year, 4) && !factor?(year, 100)
  end

  def self.factor?(number, possible_factor)
    number % possible_factor == 0
  end
end
