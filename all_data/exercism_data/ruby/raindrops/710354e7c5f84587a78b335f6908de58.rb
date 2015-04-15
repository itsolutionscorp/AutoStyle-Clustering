class Raindrops

  def convert(number)
    @number = number

    s = ""
    if pling?
      s << "Pling"
    end

    if plang?
      s << "Plang"
    end

    if plong?
      s << "Plong"
    end

    if s == ""
      s << @number.to_s
    end

    s

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
