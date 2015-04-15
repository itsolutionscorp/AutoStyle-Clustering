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
      matches? %r{[A-Z]{2,}}
    end

    def asking?
      matches? %r{\?$}
    end

    def silent?
      matches? ''
    end

    private

    def matches? pattern
      pattern == @message || pattern === @message
    end
  end
end
