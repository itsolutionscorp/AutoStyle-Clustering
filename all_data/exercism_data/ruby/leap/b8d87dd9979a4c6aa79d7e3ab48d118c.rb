class Year
  def self.leap?(year)
    return if year % 100 == 0 unless year % 400 == 0
    year % 4 == 0
  end
end
