class SecretHandshake
  COMMANDS = ['wink','double blink','close your eyes','jump', :reverse]

  def initialize(n)
    @n = n
  end

  def commands
    @commands ||= process_code
  end

  private

  def process_code
    reverse_if_needed(raw_commands)
  end

  def binary_string
    @n.is_a?(Fixnum) ? @n.to_s(2) : ''
  end

  def raw_commands
    binary_string.reverse.chars.zip(COMMANDS)
      .select { |e| e[0] == '1' }
      .map { |e| e[1] }
  end

  def reverse_if_needed(_raw_commands)
    if _raw_commands.delete(:reverse)
      _raw_commands.reverse!
    end
    _raw_commands
  end
end
