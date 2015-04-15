class Fixnum
  ARABIC_TO_ROMAN =  { 1000 => 'M',
                       900 => 'CM',
                       500 => 'D',
                       400 => 'CD',
                       100 => 'C',
                       90  => 'XC',
                       50  => 'L',
                       40  => 'XL',
                       10  => 'X',
                       9   => 'IX',
                       5   => 'V',
                       4   => 'IV',
                       1   => 'I' }
  def to_roman
    output = ''
    remaining = self
    ARABIC_TO_ROMAN.keys.each do |key|
      while remaining >= key
        output << ARABIC_TO_ROMAN[key]
        remaining -= key
      end
    end
    output
  end

end
