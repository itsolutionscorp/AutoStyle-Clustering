class Year < Struct.new(:year)
  def leap?
    year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)
  end
end
