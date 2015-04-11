class Fixnum
  def to_roman
    Roman.new(self).convert
  end
end


class Roman
  attr_reader :num

  MAPPINGS = { 1 => 'I',
               5 => 'V',
               10 => 'X',
               50 => 'L',
               100 => 'C',
               500 => 'D',
               1000 => 'M' }

  def initialize(num)
    @num = num
  end

  def convert
    MAPPINGS[num]
  end
end
