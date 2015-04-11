# Encoding: utf-8

# Clase Raindrops
class Raindrops
  def convert(n)
    s = ''
    s << 'Pling' if pling?(n)
    s << 'Plang' if plang?(n)
    s << 'Plong' if plong?(n)
    s << number.to_s if s == ''
  end

  def pling?(n)
    (n % 3) == 0
  end

  def plang?(n)
    (n % 5) == 0
  end

  def plong?(n)
    (n % 7) == 0
  end
end
