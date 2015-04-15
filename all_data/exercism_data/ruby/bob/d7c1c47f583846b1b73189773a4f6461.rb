class Bob
  def hey(communication)
    case
    when is_yelling?(communication)
      'Woah, chill out!'
    when is_question?(communication)
      'Sure.'
    when is_nothing?(communication)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

  def is_question?(communication)
    communication =~ /\?\z/
  end

  def is_yelling?(communication)
    communication =~ /[A-Z]/ && communication !~ /[a-z]/
  end

  def is_nothing?(communication)
    communication.strip.empty?
  end
end
