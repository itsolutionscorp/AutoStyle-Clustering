class Fixnum

  def to_roman
    Roman.new(self).convert_to_romans
  end

end

class Roman
  attr_reader :number
  ROMANS = { '1' => 'I', '5' => 'V', '10' => 'X', '50' => 'L', '100' => 'C', '500' => 'D', '1000' => 'M' }

  def initialize(number)
    @number = number.to_s
  end

  def convert_to_romans
    number_to_array.map { |num| convert(num) }.join
  end

  def number_to_array
    num_array = []
    @number.each_char.with_index { |char, index| num_array << (char + '0' * (@number.length - (index+1))) }
    num_array
  end

  def convert(number)
    case number.to_s[0]
    when '1', '2', '3'
      ROMANS['1' + number[1..-1]] * number[0].to_i
    when '4'
      ROMANS['1' + '0'*(number.length-1)] + ROMANS['5' + '0'*(number.length-1)]
    when '5'
      ROMANS[number]
    when '6', '7', '8'
      ROMANS['5' + '0'*(number.length-1)] + (ROMANS['1' + '0'*(number.length-1)] * (number[0].to_i-5))
    when '9'
      ROMANS['1' + '0'*(number.length-1)] + ROMANS['1' + '0'*(number.length)]
    else # when 0
      ''
    end
  end
end
