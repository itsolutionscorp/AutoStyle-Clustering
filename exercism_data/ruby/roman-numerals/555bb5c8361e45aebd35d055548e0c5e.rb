module Roman

  NUMBER_CORRESPONDANCE = {
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
    1    => 'I',
    0    => ''
  }

  def to_roman
    if NUMBER_CORRESPONDANCE.has_key? self
      NUMBER_CORRESPONDANCE[ self ]
    else
       NUMBER_CORRESPONDANCE[ best_roman ] + ( self - best_roman ).to_roman
    end
  end

private

  def best_roman
    NUMBER_CORRESPONDANCE.keys.find do |value|
      value < self
    end
  end

end

Fixnum.send( :include, Roman )
