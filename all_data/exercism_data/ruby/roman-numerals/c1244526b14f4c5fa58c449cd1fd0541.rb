class Fixnum
  def to_roman
    i = self
    {
      M:  1000,
      CM: 900,
      D:  500,
      CD: 400,
      C:  100,
      XC: 90,
      L:  50,
      XL: 40,
      X:  10,
      IX: 9,
      V:  5,
      IV: 4,
      I:  1,
    }.inject("") do |res, (name, value)|
      n, i = i.divmod(value)
      res << name.to_s * n
    end
  end
end
