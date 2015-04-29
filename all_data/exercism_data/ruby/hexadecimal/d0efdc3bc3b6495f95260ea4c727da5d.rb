class Hexadecimal

  attr_accessor :hex

  def initialize(hex)
    @hex = hex.to_i(16)
    @hex = 0 if !valid?(hex)
  end

  def to_decimal
    hex.to_s(10).to_i
  end

  private
  def valid?(hex)
    !hex[/\H/]
  end

end
