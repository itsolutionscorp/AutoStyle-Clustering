class SecretHandshake
  def initialize(n)
    @n = n
  end

  def commands
    direct COMMANDS.select.with_index {|command, index| n[index] == 1 }
  end

  private

  attr_reader :n
  COMMANDS = %w[wink double\ blink close\ your\ eyes jump]

  def direct ion
    n[4] == 1 ? ion.reverse : ion
  end
end
