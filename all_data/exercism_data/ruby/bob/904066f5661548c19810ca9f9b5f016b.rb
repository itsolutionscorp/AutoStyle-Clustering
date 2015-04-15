class Bob
  def hey sentence
    message = Message.new(sentence)
    case message.type
    when :question
      question_answer
    when :shout
      shout_answer
    when :silence
      silence_answer
    else
      default_answer
    end
  end

private
  def default_answer
    'Whatever.'
  end

  def shout_answer
    'Woah, chill out!'
  end

  def question_answer
    'Sure.'
  end

  def silence_answer
    'Fine. Be that way!'
  end
end

class Message
  def initialize message
    @message = message
  end

  def type
    return :silence if silence?
    return :shout if shouted?
    question? ? :question : :default
  end

  def shouted?
    @message[/[A-Z]/] && !@message[/[a-z]/]
  end

  def question?
    @message.end_with?('?')
  end

  def silence?
    @message.strip.empty?
  end
end
