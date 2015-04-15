class Raindrops
  def convert(n)
    unless isPling?(n) || isPlang?(n) || isPlong?(n)
      return n.to_s
    end
    string=''
    if isPling?(n) then string<<'Pling'
    end
    if isPlang?(n) then string<<'Plang'
    end
    if isPlong?(n) then string<<'Plong'
    end
    return string
  end

  def isPling?(n)
    n % 3 == 0
  end

  def isPlang?(n)
    n % 5 == 0
  end

  def isPlong?(n)
    n % 7 == 0
  end
end
