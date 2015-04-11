class Bob

  attr_reader :interpreter
  def initialize
    @interpreter = BobsLanguageInterpreter.new
  end

  def hey(message)
    interpreter.last_message = message
    if interpreter.nonesense?
      'Fine. Be that way!'
    elsif interpreter.loud_yapping?
      'Woah, chill out!'
    elsif interpreter.question?
      'Sure.'
    else
      'Whatever.'
    end
  end


  class BobsLanguageInterpreter

    attr_reader :last_message
    def last_message=(message)
      @last_message = message
    end

    def nonesense?
      last_message.strip.empty?
    end

    def loud_yapping?
      last_message == last_message.upcase
    end

    def question?
      last_message.end_with? '?'
    end

  end

end
