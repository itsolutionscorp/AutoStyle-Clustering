class Fixnum
  def to_roman
    Roman.new(self).convert
  end
end


class Roman
  attr_reader :num

  MAPPINGS = { 1000 => 'M',
               900 => 'CM',
               500 => 'D',
               400 => 'CD',
               100 => 'C',
               90 => 'XC',
               50 => 'L',
               40 => 'XL',
               10 => 'X',
               9 => 'IX',
               5 => 'V',
               4 => 'IV',
               1 => 'I'}

  def initialize(num)
    @num = num
  end

  def convert
    str = ""
    MAPPINGS.sort{|a,b| b[0] <=> a[0]}.each do |val, sym|
      occurances, leftover = num.divmod(val)
      str += sym * occurances
      @num = leftover
    end
    str
  end
end
