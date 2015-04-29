class SecretHandshake

  HANDSHAKES = { 
                  1     => "wink",
                  10    => "double blink",
                  100   => "close your eyes",
                  1000  => "jump"
  }

  attr_reader :decimal_number

  def initialize decimal_number
    @decimal_number = decimal_number
  end

  def commands
    if decimal_number.is_a? Integer
      secret_handshakes
    else
      []
    end
  end

private

  def binary_number
    decimal_number.to_s 2
  end

  def translator
    result = Array.new

    binary_number.chars.reverse.each_with_index do |digit, index|
      result << HANDSHAKES[ digit.ljust( index + 1, '0' ).to_i ]
    end

    result.compact
  end

  def secret_handshakes
    if binary_number.length == 5
      translator.reverse
    else
      translator
    end
  end

end
