class Bob

  def hey(message)
    interpreter = BobsLanguageInterpreter.new
    interpreter.message = message

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

    attr_accessor :message

    def nonesense?
      message.strip.empty?
    end

    def loud_yapping?
      message == message.upcase
    end

    def question?
      message.end_with? '?'
    end

  end

end
