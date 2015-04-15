class Year
  def self.leap?(year)
    # Return true if year is leap, false otherwise
    ((year % 4 == 0) and (year % 100 != 0)) or (year % 400 == 0)
  end
end
