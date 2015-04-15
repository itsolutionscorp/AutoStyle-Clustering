class Year
  def self.leap?(year)
    every_fourth(year) && (not_every_100th(year) || every_four_hundredth(year))
  end

  def self.every_fourth(year)
    year % 4 == 0
  end

  def self.not_every_100th(year)
    year % 100 != 0
  end

  def self.every_four_hundredth(year)
    year % 400 == 0
  end
end
