class Raindrops

  def convert(num)
    unless pling?(num) || plang?(num) || plong?(num)
      return num.to_s
    end
    s = ''
    s << 'Pling' if pling?(num)
    s << 'Plang' if plang?(num)
    s << 'Plong' if plong?(num)
    s
  end

  def pling?(number)
    (number % 3) == 0
  end

  def plang?(number)
    (number % 5) == 0
  end

  def plong?(number)
    (number % 7) == 0
  end

end
