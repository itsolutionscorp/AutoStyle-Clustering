class Fixnum

Numerals = { 'M' => 1000, 'CM' => 900, 'D' => 500, 'CD' => 400,
             'C' => 100, 'XC' => 90, 'L' => 50, 'XL' => 40,
             'X' => 10, 'IX' => 9, 'V' => 5, 'IV' => 4, 'I' => 1 }

  def to_roman
    n = self
    Numerals.each_with_object("") { |(k, v), s|
      (q, n) = n.divmod(v)
      s << k * q
    }
  end

end
