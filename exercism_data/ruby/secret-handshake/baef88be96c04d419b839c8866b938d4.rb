class SecretHandshake
  def initialize(code)
    @indexes, @reverse = parse_code(code)
  end

  def commands
    GESTURES.values_at(*@indexes).tap do |result|
      result.reverse! if @reverse
    end
  end

private
  
  GESTURES = ["wink", "double blink", "close your eyes", "jump"]

  def parse_code(code)
    flags = string_code(code).reverse
    indexes = 0.upto(GESTURES.length - 1).select { |i| is_on flags[i] }.compact
    [indexes, is_on(flags[GESTURES.length])]
  end

  def string_code(code)
    code.is_a?(Integer) ? code.to_s(2) : code
  end

  def is_on(flag)
    flag == "1"
  end
end
