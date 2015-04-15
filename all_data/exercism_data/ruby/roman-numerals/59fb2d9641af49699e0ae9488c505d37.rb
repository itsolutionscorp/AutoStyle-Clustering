class Integer
  D2R = { # order matters, otherwise I'd compose the compounds separately
         1000 => "M",
         900  => "CM",
         500  => "D",
         400  => "CD",
         100  => "C",
         90   => "XC",
         50   => "L",
         40   => "XL",
         10   => "X",
         9    => "IX",
         5    => "V",
         4    => "IV",
         1    => "I",
        }

  def to_roman
    n = self
    result = []

    D2R.each do |decimal, roman|
      quotient, remainder = n.divmod decimal

      result << roman * quotient if quotient > 0

      n = remainder
    end

    result.join
  end
end
