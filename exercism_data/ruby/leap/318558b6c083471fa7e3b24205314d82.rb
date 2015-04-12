class Year
  def self.leap?(year)
    year % 4 == 0 && !(year % 100 == 0 unless year % 400 == 0)
  end
end