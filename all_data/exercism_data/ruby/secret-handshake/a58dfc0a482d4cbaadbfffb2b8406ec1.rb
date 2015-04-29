class SecretHandshake
  attr_reader :binary
  def initialize(decimal)
    @binary = decimal.to_s(2).reverse.chars.collect(&:to_i)
  rescue ArgumentError
    @binary = 0
  end

  def commands
    handshake = []
    (0..3).each do |index|
      if binary[index] == 1
        handshake << signals[index]
      end
    end
    if binary[4] == 1
      handshake.reverse
    else
      handshake
    end
  end

  private

  def signals
    ['wink', 'double blink', 'close your eyes', 'jump']
  end
end
