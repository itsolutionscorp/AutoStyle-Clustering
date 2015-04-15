class SecretHandshake
  def initialize(decimal_number)
    @decimal_number = decimal_number.to_i
  end

  COMMANDS = ['jump', 'close your eyes', 'double blink', 'wink']
  def commands
    reverse, @decimal_number = @decimal_number.divmod(16)
    commands = COMMANDS.map.with_index do |command, index|
      quotient, @decimal_number = @decimal_number.divmod(2**(3-index))
      command if quotient > 0
    end.compact
    reverse > 0 ? commands : commands.reverse
  end
end
