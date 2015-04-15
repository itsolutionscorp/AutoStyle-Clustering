class Bob

  def hey(command)

    direction = Command.new(command)

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

class Command
  attr_reader :command

  def initialize(command)
    @command = command
  end

  def silence?
    command.strip.empty?
  end

  def yelling?
    command == command.upcase
  end

  def question?
    command.end_with?("?")
  end 
end
