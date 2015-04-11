class Fixnum
  def to_roman
    r_thousands(self) + r_hundreds(self) + r_tens(self) + r_units(self)
  end

  private

  def thousands(n)
    (n.to_i / 1000)
  end

  def hundreds(n)
    (n.to_i - n.to_i / 1000 * 1000) / 100
  end

  def tens(n)
    (n.to_i - n.to_i / 100 * 100) / 10
  end

  def units(n)
    n.to_i - n.to_i / 10 * 10
  end

  def r_thousands(n)
    'M' * thousands(n)
  end

  def r_hundreds(n)
    roman_construction hundreds(n), 'C', 'D', 'M'
  end

  def r_tens(n)
    roman_construction tens(n), 'X', 'L', 'C'
  end

  def r_units(n)
    roman_construction units(n), 'I', 'V', 'X'
  end

  def roman_construction(nb, element, half, full)
    case nb
    when 0..3
      element * nb
    when 4
      element + half
    when 5..8
      half + element * (nb - 5)
    when 9
      element + full
    end
  end
end
