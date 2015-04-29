class Integer

  CONVERSION_HASH = {
    1000 => 'M',
    900  => 'CM',
    500  => 'D',
    400  => 'CD',
    100  => 'C',
    90   => 'XC',
    50   => 'L',
    40   => 'XL',
    10   => 'X',
    9    => 'IX',
    5    => 'V',
    4    => 'IV',
    1    => 'I'
  }

  def to_roman
    to_convert = self
    romans = ''

    CONVERSION_HASH.keys.each do |hash_key|
      while to_convert >= hash_key
        to_convert -= hash_key
        romans += CONVERSION_HASH[hash_key]
      end
    end
    romans
  end

end
