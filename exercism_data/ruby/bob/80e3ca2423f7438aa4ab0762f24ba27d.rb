module StringValidations

  def contains_hyphens_and_period_at_end?(str)
    str.match(/\-|\.$/)
  end

  def contains_uppercase_words?(str)
    str.match(/[A-Z]{2,}/)
  end

  def contains_question_mark?(str)
    str.match(/\?$/)
  end

  def contains_exclamation_mark?(str)
    str.match(/\!$/)
  end

  def contains_ok?(str)
    str.match(/OK/)
  end
end

class Message
  include StringValidations
  attr_reader :message

  def initialize(message)
    super()
    @message = message
  end

  def validate_to_respond
    if @message.nil? or @message==''
      'Fine. Be that way!'
    elsif contains_hyphens_and_period_at_end?(@message) or (contains_exclamation_mark?(@message) and !contains_uppercase_words?(@message))
      'Whatever.'
    elsif contains_uppercase_words?(@message) and !(contains_ok?(@message) and contains_question_mark?(@message))
      'Woah, chill out!'
    elsif contains_question_mark?(@message)
      'Sure.'
    end
  end

end

class Bob

  def hey(message)
    response = Message.new(message)
    response.validate_to_respond
  end

end
