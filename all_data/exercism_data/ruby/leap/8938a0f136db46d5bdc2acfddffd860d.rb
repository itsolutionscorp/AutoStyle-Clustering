class Year
  def self.leap?(year)
    by_4?(year) && (!by_100?(year) || by_400?(year))
  end

  def self.by_4?(year)
    year % 4 == 0
  end

  def self.by_100?(year)
    year % 100 == 0
  end

  def self.by_400?(year)
    year % 400 == 0
  end
end
