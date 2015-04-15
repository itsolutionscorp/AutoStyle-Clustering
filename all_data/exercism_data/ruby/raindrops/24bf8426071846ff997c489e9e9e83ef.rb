class Raindrops

  def convert(number)
    pling = nil
    plang = nil
    plong = nil
    (0..number).each do |i|
      if has_threes?(i, number)
        pling = "Pling"
      elsif has_fives?(i, number)
        plang = "Plang"
      elsif has_sevens?(i, number)
        plong = "Plong"
      end
    end
    if pling || plang || plong
      return "#{pling}#{plang}#{plong}"
    else
      return "#{number}"
    end
  end

  def has_threes?(i, number)
    i * 3 == number
  end

  def has_fives?(i, number)
    i * 5 == number
  end

  def has_sevens?(i, number)
    i * 7 == number
  end

end
