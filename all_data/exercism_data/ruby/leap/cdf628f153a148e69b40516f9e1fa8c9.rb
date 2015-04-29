class Year
  def self.leap?(year)
   return false unless year % 4 == 0 && year % 100 != 0 || year % 400 === 0
   return true
  end
end
