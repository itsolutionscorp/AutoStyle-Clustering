class SecretHandshake
  attr_reader :binary

  def initialize(input)
    @binary = clean_input(input)
  end

  def clean_input(input)
    input.kind_of?(Integer) ? to_binary(input) : []
  end

  def to_binary(input)
    input.to_s(2).chars.reverse
  end

  def output
    {
      0 => "wink",
      1 => "double blink",
      2 => "close your eyes",
      3 => "jump"
    }
  end

  def commands
    reverse_order? ? code.reverse : code
  end

  def reverse_order?
    binary.length == 5
  end

  def code
    binary.length.times.map { |i|
      output[i] if binary[i] == "1"
    }.compact
  end
end
