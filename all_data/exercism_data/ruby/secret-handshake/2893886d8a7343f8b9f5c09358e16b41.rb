class SecretHandshake

  attr_reader :binary

  def initialize(decimal)
    @binary = decimal.to_i.to_s(2)
  end

  def commands
    binary.reverse.each_char.with_index.with_object([]) do |(char, index), handshake|
      if index == 4
        handshake.reverse!
      else 
        handshake << COMMAND_LIST[index] if char == "1"
      end
    end
  end

  HANDSHAKE = {
    "1" => "wink",
    "10" => "double blink",
    "100" => "close your eyes",
    "1000" => "jump"
  }

  COMMAND_LIST = HANDSHAKE.values

end
