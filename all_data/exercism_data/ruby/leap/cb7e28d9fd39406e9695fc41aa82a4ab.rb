class Year
  def self.leap?(year)
    leap = if year % 4 == 0
             true unless year % 100 == 0 && year % 400 != 0
           end
    leap || false
  end
end
