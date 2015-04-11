class SecretHandshake
  COMMANDS = ["wink", "double blink", "close your eyes", "jump"]

  def initialize(code)
    @code = code.to_i
  end

  def commands
    code = @code
    command_list = COMMANDS.each.with_object([]) do |command, list|
      list << command if code & 1 == 1
      code >>= 1
    end
    command_list.reverse! if code == 1
    command_list
  end
end
