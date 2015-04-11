class Year
  def self.leap?(year)
    fourth? year and (not century? year or fourth_century? year)
  end

  def self.fourth?(year)
    year % 4 == 0
  end

  def self.century?(year)
    year % 100 == 0
  end

  def self.fourth_century?(year)
    year % 400 == 0
  end
end
