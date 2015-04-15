class Raindrops

  def convert(number)
    @number = number

    if pling_plang_plong
      "PlingPlangPlong"
    elsif pling_plang
      "PlingPlang"
    elsif pling_plong
      "PlingPlong"
    elsif plang_plong
      "PlangPlong"
    elsif pling
      "Pling"
    elsif plang
      "Plang"
    elsif plong
      "Plong"
    else
      number.to_s
    end

  end

  def pling_plang_plong
    pling && plang && plong
  end

  def pling_plang
    pling && plang
  end

  def pling_plong
    pling && plong
  end

  def plang_plong
    plang && plong
  end

  def pling
    @number % 3 == 0
  end

  def plang
    @number % 5 == 0
  end

  def plong
    @number % 7 == 0
  end

end
