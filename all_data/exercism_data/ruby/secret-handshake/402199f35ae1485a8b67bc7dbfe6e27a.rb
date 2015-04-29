class SecretHandshake
  COMMAND_LIST = [
    'wink',
    'double blink',
    'close your eyes',
    'jump'
  ]

  def initialize(decimal_num)
    @binary_num = decimal_num.to_i.to_s(2)
  end

  def commands
    binary_num.reverse.each_char.with_index.with_object([]) do |(char, index), list|
      if index == 4
        list.reverse!
      else
        list << COMMAND_LIST[index] if char == '1'
      end
    end
  end

  private

  attr_reader :binary_num
end
