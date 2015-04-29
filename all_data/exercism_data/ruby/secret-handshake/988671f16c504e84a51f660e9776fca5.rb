class SecretHandshake
  def initialize(code)
    @flags, @reverse = parse_code(code)
  end

  def commands
    COMMANDS.values_at(*@flags).tap do |result|
      result.reverse! if @reverse
    end
  end

private
  
  COMMANDS = ["wink", "double blink", "close your eyes", "jump"]

  def parse_code(code)
    string_code = code.is_a?(String) ? code.reverse : code.to_s(2).reverse
    flags = 0.upto(3).select { |i| string_code[i] == "1" }.compact
    reverse = string_code[4]
    [flags, reverse]
  end

end
