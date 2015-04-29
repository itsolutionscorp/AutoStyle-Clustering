class Year
  def self.leap? (year)
    if year % 4 == 0
      "It is a leap year!"
    elsif
      (year % 4 == 0) && (year % 100 == 0)
      "It is not a leap year."
    elsif
      (year % 400 == 0) && (year % 100 == 0)
      "Yes, it is a leap year!"
    end
  end
end
