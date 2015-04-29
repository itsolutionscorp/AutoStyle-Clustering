class Bob

  def initialize
    @interpreter = BobsLanguageInterpreter.new
  end

  def hey(message)
    return 'Fine. Be that way!' if @interpreter.is_this_nonesense? message
    return 'Woah, chill out!' if @interpreter.is_this_loud_yapping? message
    return 'Sure.' if @interpreter.is_this_a_question? message
    'Whatever.'
  end


  class BobsLanguageInterpreter

    def is_this_nonesense?(message)
      message.strip.empty?
    end

    def is_this_loud_yapping?(message)
      message == message.upcase
    end

    def is_this_a_question?(message)
      message.end_with? '?'
    end

  end

end
