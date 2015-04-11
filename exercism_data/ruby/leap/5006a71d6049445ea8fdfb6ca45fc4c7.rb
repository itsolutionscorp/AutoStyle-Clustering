class Year
  def self.leap?(year)
   return true if (year % 400 == 0) or (year % 100 !=0) && (year % 4 == 0)
   false
  end
end
