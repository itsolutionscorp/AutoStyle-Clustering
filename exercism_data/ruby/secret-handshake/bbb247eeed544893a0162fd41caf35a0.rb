class SecretHandshake
  def initialize(number)
    @number = number.to_i
  end

  def commands
    list = []
    list << "wink"            if match?(0b1)
    list << "double blink"    if match?(0b10)
    list << "close your eyes" if match?(0b100)
    list << "jump"            if match?(0b1000)
    list.reverse!             if match?(0b10000)
    list
  end

  private

  def match?(binary)
    (@number & binary).nonzero?
  end
end
