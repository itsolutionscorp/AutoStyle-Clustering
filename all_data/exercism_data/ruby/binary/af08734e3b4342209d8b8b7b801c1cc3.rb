class Binary
  attr_reader :number, :decimal

  def initialize(number)
  @number = number.chars
  @decimal = 0
  end

  def to_decimal
    calc_decimal if number.all? {|n| [0,1].include?(n.to_i) }
    decimal
  end

  def calc_decimal
    number.length.times do |i|
      num = number[-1-i].to_i
      @decimal += num*(2**i)
    end
  end
end
