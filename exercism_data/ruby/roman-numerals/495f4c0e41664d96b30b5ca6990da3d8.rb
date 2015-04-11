# roman.rb
# Roman Numerals Exercise

# Algorithm somewhat stolen from "Programming Ruby 1.9 & 2.0 p354
Numerals = { 'M' => 1000, 'CM' => 900, 'D' => 500, 'CD' => 400,
             'C' => 100, 'XC' => 90, 'L' => 50, 'XL' => 40,
             'X' => 10, 'IX' => 9, 'V' => 5, 'IV' => 4, 'I' => 1 }

class Integer
  def to_roman
    n = self
    s = ""
    Numerals.each do |k,v|
      q,n = n.divmod(v)
      s << k * q
    end
    s
  end
end
