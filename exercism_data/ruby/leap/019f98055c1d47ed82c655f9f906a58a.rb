class Year
  def self.leap?(year)
    if year % 100 == 0
      return year % 400 == 0
    end
    year % 4 == 0
  end
end
