class String
  def get_digits
    scan(/\d/).map(&:to_i).reverse if match(/\A[01]\z/) || []
  end
end

class Fixnum
  def get_digits
    d = []
    digits(2) { |i| d.push i }
    d
  end

  private
  def digits(n)
    unless block_given?
      return enum_for{__method__}
    end

    remainder = self
    while remainder > 0
      yield remainder % n
      remainder = remainder / n
    end
  end
end

class SecretHandshake
  attr_reader :commands

  COMMANDS = "wink:double blink:close your eyes:jump".split ":"

  def initialize(message)
    @commands = commands_for message.get_digits 
  end

  private
  def commands_for(digits)
    i = 0
    digits.each_with_object([]) do |digit, command_list|
      if digit == 1
        return command_list.reverse if i == 4
        command_list.push COMMANDS[i] if digit == 1
      end
      i += 1
    end
  end
end
