class Year
  def self.leap?(year)
    (Date.new(year, 1, 1) + 365).year == year
  end
end
