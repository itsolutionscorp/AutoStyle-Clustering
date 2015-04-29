class Bob
  def hey(message)
    Statement.new(message).reply_with(replies)
  end

  def replies
    @replies ||= {
      silence:  'Fine. Be that way!',
      shouting: 'Woah, chill out!',
      question: 'Sure.',
      default:  'Whatever.'
    }
  end
end

class Statement
  def initialize(message)
    @message = message
  end

  def reply_with(replies)
    case
    when silence?  then replies[:silence]
    when shouting? then replies[:shouting]
    when question? then replies[:question]
    else replies[:default]
    end
  end

  private

  def silence?
    @message.strip.empty?
  end

  def shouting?
    @message.match(/[A-Z]/) && !@message.match(/[a-z]/)
  end

  def question?
    @message.end_with?('?')
  end
end
