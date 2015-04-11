class Bob

  def hey(command_to_bob)

    direction = CommandToBob.new(command_to_bob)

    case
    when direction.silence?
      "Fine. Be that way!"
    when direction.yelling?
      "Woah, chill out!"
    when direction.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class CommandToBob
  attr_reader :command_to_bob

  def initialize(command_to_bob)
    @command_to_bob = command_to_bob
  end

  def silence?
    command_to_bob.strip.empty?
  end

  def yelling?
    command_to_bob == command_to_bob.upcase
  end

  def question?
    command_to_bob.end_with?("?")
  end 

end
