class Bob
  def hey(what_they_said)
    what_i_heard = interpret(what_they_said)

    if what_i_heard.is_silence?
      'Fine. Be that way!'
    elsif what_i_heard.is_shouty?
      'Woah, chill out!'
    elsif what_i_heard.is_a_question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def interpret(message)
    Interpretation.new(message)
  end

  class Interpretation < Struct.new(:message)
    def is_silence?
      message.nil? || message.strip == ''
    end

    def is_shouty?
      message =~ /[A-Z]/ && message.upcase == message
    end

    def is_a_question?
      message.end_with?('?')
    end
  end
end
