class Bob

  def hey(greeting)
    statement = Statement.new(greeting)
    case
    when statement.yelling?
      'Woah, chill out!'
    when statement.question?
      'Sure.'
    when statement.passive_aggressive?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class Statement
  attr_accessor :message
  def initialize(greeting)
    @message = greeting
  end

  def question?
    message.end_with?('?')
  end

  def yelling?
    message == message.upcase && message.match(/[a-zA-Z]/)
  end

  def passive_aggressive?
    message == '' || message[0,1] == ' '
  end
end
