class Fixnum

  ROMANS = {
    1000 =>  'M', 900 => 'CM', 500 =>  'D', 400 => 'CD',
     100 =>  'C',  90 => 'XC',  50 =>  'L',  40 => 'XL',
      10 =>  'X',   9 => 'IX',   5 =>  'V',   4 => 'IV',
       1 =>  'I'
  }

  def to_roman
    x = self
    ROMANS.inject("") do |s, o|
      while x >= o[0]
        x -= o[0]
        s += o[1]
      end; s
    end
  end
end
