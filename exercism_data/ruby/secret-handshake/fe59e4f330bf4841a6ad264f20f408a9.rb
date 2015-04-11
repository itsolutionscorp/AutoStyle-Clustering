class SecretHandshake
  attr_reader :number
  def initialize(number)
    @number = number
  end

  def commands
    remaining_total = number
    commands = []
    reverse = false

    if(remaining_total % 16 == 0)
      reverse = true
      remaining_total -= 16
    end

    if (remaining_total >= 8)
      commands << 'jump'
      remaining_total -= 8
    end

    if (remaining_total >= 4)
      commands << 'close your eyes'
      remaining_total -= 4
    end

    if (remaining_total >= 2)
      commands << 'double blink'
      remaining_total -= 2
    end

    if (remaining_total >= 1)
      commands << 'wink'
      remaining_total -= 1
    end

    if reverse
      commands.reverse
    else
      commands
    end
  end
end
