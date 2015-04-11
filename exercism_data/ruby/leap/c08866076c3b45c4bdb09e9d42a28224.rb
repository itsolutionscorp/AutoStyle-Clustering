class Year

  def initialize year
    @year = year
  end

  def leap?
    @year.modulo(400)  == 0 ||
    (@year.modulo(4)   == 0 unless 
    @year.modulo(100)  == 0)
  end

end
