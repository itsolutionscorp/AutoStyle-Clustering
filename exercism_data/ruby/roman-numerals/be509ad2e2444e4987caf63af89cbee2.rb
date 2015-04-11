class Fixnum
  def to_roman
    num = self
    roman = []
    table = {   1 => 'I',
                4 => 'IV',
                5 => 'V',
                9 => 'IX',
               10 => 'X',
               40 => 'XL',
               50 => 'L',
               90 => 'XC',
              100 => 'C',
              400 => 'CD',
              500 => 'D',
              900 => 'CM',
             1000 => 'M'}
    table.keys.sort.reverse.each do |div|
      while num / div > 0
        num -= div
        roman << table[div]
      end
    end
    roman.join
  end
end
