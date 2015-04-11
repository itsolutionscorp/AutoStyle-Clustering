class SecretHandshake
  ALL_COMMANDS = ["wink", "double blink", "close your eyes", "jump"]
  def initialize(number)
    @number = number
  end

  def commands
    return [] unless @number.is_a? Fixnum
    binary = @number.to_s(2).rjust(5, "0").reverse
    commands = binary[0..-2].chars.each_with_index.reduce([]) do |cmds, (char, index)|
      char == "1" ? cmds << ALL_COMMANDS[index] : cmds
    end
    binary[-1] == "1" ? commands.reverse : commands
  end
end
