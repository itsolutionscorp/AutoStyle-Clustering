class Fixnum
  ROMAN_MAP = { 1_000 => 'M',
                900   => 'CM',
                500   => 'D',
                400   => 'CD',
                100   => 'C',
                90    => 'XC',
                50    => 'L',
                40    => 'XL',
                10    => 'X',
                9     => 'IX',
                5     => 'V',
                4     => 'IV',
                1     => 'I' }

  def to_roman
    ROMAN_MAP.reduce(out: [], num: self) do |a, (key, numeral)|
      ocurrences, remainder = a[:num].divmod(key)
      { out: (a[:out] << (numeral * ocurrences)), num: remainder }
    end[:out].join
  end
end
