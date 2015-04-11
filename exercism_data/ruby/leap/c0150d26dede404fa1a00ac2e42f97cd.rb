class Year
  def Year.leap?(year)
    year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)
  end
end
