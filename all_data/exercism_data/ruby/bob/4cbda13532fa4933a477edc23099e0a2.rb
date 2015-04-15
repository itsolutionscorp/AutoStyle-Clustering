class Bob
  def hey(talk)
    message = Message.new(talk)
    if message.yelling?
      'Woah, chill out!'
    elsif message.question?
      'Sure.'
    elsif message.silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class Message
  attr_reader :talk

  def initialize(talk)
    @talk = talk
  end

  def yelling?
    talk.upcase == talk && talk =~ /[[:alpha:]]/
  end

  def question?
    talk.end_with?('?')
  end

  def silence?
    talk.strip.empty?
  end
end
