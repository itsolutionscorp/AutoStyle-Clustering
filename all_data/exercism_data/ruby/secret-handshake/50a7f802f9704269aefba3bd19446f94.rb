class SecretHandshake
  COMMANDS = [
    [:<<, "wink"],
    [:<<, "double blink"],
    [:<<, "close your eyes"],
    [:<<, "jump"],
    [:reverse!]
  ]

  def initialize(number)
    @number = number.to_i
  end

  def commands
    COMMANDS.each.with_index.with_object([]) do |(action, bit), result|
      result.send(*action) if bit_enabled?(bit)
    end
  end

  private

  attr_reader :number

  def bit_enabled?(bit)
    mask = 2**bit
    number & mask == mask
  end
end
