class Roman

  NUMERALS = {
    "M" => 1000,
    "CM" => 900,
    "D" => 500,
    "CD" => 400,
    "C" => 100,
    "XC" => 90,
    "L" => 50,
    "XL" => 40,
    "X" => 10,
    "IX" => 9,
    "V" => 5,
    "IV" => 4,
    "I" => 1
  }

  # ONLY add if there is no existing monkeypatch
  if Fixnum.respond_to? :to_roman
    raise "Two classes are monkeypatching Fixnum for the same method.  Obvious errors will occurr."
  else
    Fixnum.class_eval do
      define_method(:to_roman) {Roman.romanize self}
    end
  end

  def self.romanize current
    numerals = ""
    NUMERALS.each do |rn|
      quantity = current / rn[1]
      numerals += rn[0] * quantity
      current -= rn[1] * quantity
    end

    numerals
  end

end
