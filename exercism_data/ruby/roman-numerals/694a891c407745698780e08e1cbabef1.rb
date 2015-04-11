class Integer
  ROMAN_SEQUENCES = [
    ['M', 1000], ['CM', 900],
    ['D',  500], ['CD', 400],
    ['C',  100], ['XC',  90],
    ['L',   50], ['XL',  40],
    ['X',   10], ['IX',   9],
    ['V',    5], ['IV',   4],
    ['I',    1]
  ]

  def to_roman
    remainder = self
    roman = ""
    ROMAN_SEQUENCES.each do |sequence, value|
      while remainder >= value
        roman << sequence
        remainder -= value
      end
    end
    roman
  end
end
