class Raindrops

  def convert(number)
    @number = number

    if pling? && plang? && plong?
      "PlingPlangPlong"
    elsif pling? && plang?
      "PlingPlang"
    elsif pling? && plong?
      "PlingPlong"
    elsif plang? && plong?
      "PlangPlong"
    elsif pling?
      "Pling"
    elsif plang?
      "Plang"
    elsif plong?
      "Plong"
    else
      number.to_s
    end

  end

  def pling?
    @number % 3 == 0
  end

  def plang?
    @number % 5 == 0
  end

  def plong?
    @number % 7 == 0
  end

end
