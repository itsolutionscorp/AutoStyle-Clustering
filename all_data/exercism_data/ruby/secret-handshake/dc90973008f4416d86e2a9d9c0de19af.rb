class SecretHandshake
  TYPES = [
    'wink',
    'double blink',
    'close your eyes',
    'jump',
  ]

  def initialize bit_pattern
    @bit_pattern = bit_pattern.to_i
  end

  def commands
    commands =
      TYPES.select.with_index do |_, index|
        bit_on? index
      end
    commands.reverse! if reversed?
    commands
  end

  private

  def reversed?
    bit_on? 4
  end

  def bit_on? bit
    0 != @bit_pattern & (2**bit)
  end
end
