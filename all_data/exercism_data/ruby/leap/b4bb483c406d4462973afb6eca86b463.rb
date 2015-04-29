class Year < Struct.new :year
  def leap?
    return true if year % 400 == 0
    return false if year % 100 == 0
    year % 4 == 0
  end
end
