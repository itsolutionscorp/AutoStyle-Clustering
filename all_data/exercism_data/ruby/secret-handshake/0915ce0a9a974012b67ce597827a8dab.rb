class SecretHandshake

  COMMANDS = ["wink", "double blink", "close your eyes", "jump"]

  def initialize(n)
    @n = n
  end

  def commands
    c = []
    0.upto(COMMANDS.length) do |i|
      break if !@n.is_a? Fixnum
      if @n & (2**i) == (2**i)
        i == COMMANDS.length ? c.reverse! : c << COMMANDS[i]
      end
    end
    c
  end
end
