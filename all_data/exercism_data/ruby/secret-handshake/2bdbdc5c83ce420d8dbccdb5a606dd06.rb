class SecretHandshake
  def initialize(number)
    @number = number.to_i
  end

  def commands
    out = []
    out << "wink"            if match?(0b1)
    out << "double blink"    if match?(0b10)
    out << "close your eyes" if match?(0b100)
    out << "jump"            if match?(0b1000)
    out.reverse!             if match?(0b10000)
    out
  end

  private

  def match?(binary)
    !(@number & binary).zero?
  end
end
