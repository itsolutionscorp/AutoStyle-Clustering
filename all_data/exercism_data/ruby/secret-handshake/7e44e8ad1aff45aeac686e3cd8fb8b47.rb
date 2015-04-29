class SecretHandshake

  def initialize(decimal)
    @handshake = []
    @decimal   = decimal
    @binary    = decimal.to_s(2) if valid?(decimal)
  end

  def commands
    return @handshake if !valid?(@decimal)

    @binary.chars.reverse.each.with_index do |char, index|
      if char == "1" && index < 4
        @handshake << HANDSHAKE.values[index]
      end
    end
    @handshake.reverse! if @binary.chars.length > 3
    @handshake
  end

  private
  def valid?(decimal)
    decimal != decimal.to_s
  end

  HANDSHAKE = {
    "1" => "wink",
    "10" => "double blink",
    "100" => "close your eyes",
    "1000" => "jump"
  }

end
