module Roman
  def to_roman
    symbols = {
      1000 => "M", 900 => "CM", 500 => "D", 400 => "CD",
       100 => "C",  90 => "XC",  50 => "L",  40 => "XL",
        10 => "X",   9 => "IX",   5 => "V",   4 => "IV",
         1 => "I"
    }

    remainder = self
    symbols.each_with_object("") do |(value, symbol), buffer|
      repetitions, remainder = remainder.divmod(value)
      buffer << symbol * repetitions
    end
  end
end

Fixnum.send(:include, Roman)
