class Year
  def self.leap?(year)
    true unless year % 100 == 0 && year % 400 != 0 || year % 4 != 0
  end
end
