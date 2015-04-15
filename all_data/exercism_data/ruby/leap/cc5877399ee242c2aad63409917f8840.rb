class Year < Struct.new(:year)

  def leap?
    if    divides_by?(400) then true
    elsif divides_by?(100) then false
    elsif divides_by?(4)   then true
    else
      false
    end
  end

  private
  def divides_by?(num)
    year % num == 0
  end
end
