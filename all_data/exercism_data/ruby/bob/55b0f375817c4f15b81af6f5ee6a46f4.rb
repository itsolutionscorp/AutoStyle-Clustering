class Bob
  def hey(message)
    interlocutor = TeenageInterlocutor.new(message)
    case
    when interlocutor.yelling?
      'Woah, chill out!'
    when interlocutor.asking?
       'Sure.'
    when interlocutor.silent?
      'Fine. Be that way.'
    else
      'Whatever.'
    end
  end

  class TeenageInterlocutor
    def initialize(message)
      @message = message
    end

    def yelling?
      @message.match /[A-Z]{2,}/
    end

    def asking?
      @message.end_with? '?'
    end

    def silent?
      @message.empty?
    end
  end
end
