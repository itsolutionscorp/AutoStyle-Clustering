class Bob
  def hey(message)
    respond Person.new(message)
  end

  private

  def respond(message)
    case
      when message.yelling?
        'Woah, chill out!'
      when message.question?
        'Sure.'
      when message.quiet?
        'Fine. Be that way!'
      else
        'Whatever.'
    end
  end
end

class Person
  attr_reader :message

  def initialize(message)
    @message = message
  end

  def yelling?
    message == message.upcase && message =~ /[A-Z]+/
  end

  def question?
    message.gsub("\n", " ") =~ /\?$/
  end

  def quiet?
    message.strip == ''
  end
end
