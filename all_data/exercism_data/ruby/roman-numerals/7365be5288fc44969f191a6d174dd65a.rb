# Monkey patching class Fixnum
class Fixnum
  def to_roman
    handle_thousands(self / 1000)            +
    handle((self / 100) % 10, 'C', 'D', 'M') +
    handle((self / 10) % 10, 'X', 'L', 'C')  +
    handle(self % 10, 'I', 'V', 'X')
  end

  def handle(x, c, d, m)
    return c + m if  x == 9
    return c + d if  x == 4
    return (c * x) if x < 5
    return d + "#{ c * (x % 5) }" if x >= 5
  end

  def handle_thousands(x)
    'M' * x
  end
end
