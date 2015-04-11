class Raindrops
  def convert(drop_count)
    result = ''
    if pling?(drop_count)
      result << "Pling"
    end
    if plang?(drop_count)
      result << "Plang"
    end
    if plong?(drop_count)
      result << "Plong"
    end
    if none?(drop_count)
      result << drop_count.to_s
    end
    result
  end

  def pling?(i)
    (i % 3) == 0
  end

  def plang?(i)
    (i % 5) == 0
  end

  def plong?(i)
    (i % 7) == 0
  end

  def none?(i)
    !pling?(i) && !plang?(i) && !plong?(i)
  end
end
