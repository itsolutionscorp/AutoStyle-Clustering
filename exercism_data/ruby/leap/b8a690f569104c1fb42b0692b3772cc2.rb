class Year < Struct.new(:year)
  def leap?
    return true if end_of_century_leap_year?(year)
    return false if end_of_century?(year)

    year % 4 == 0
  end

  private

  def end_of_century_leap_year?(year)
    year % 400 == 0
  end

  def end_of_century?(year)
    year % 100 == 0
  end
end
