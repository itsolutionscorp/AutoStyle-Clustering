class Year < Struct.new(:year)
  def leap?
    return true if exceptional_century?(year)
    return false if century?(year)

    year % 4 == 0
  end

  private

  def exceptional_century?(year)
    year % 400 == 0
  end

  def century?(year)
    year % 100 == 0
  end
end
