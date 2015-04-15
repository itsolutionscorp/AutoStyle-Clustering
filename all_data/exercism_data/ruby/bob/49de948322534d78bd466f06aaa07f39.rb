class MessageProcessor
  def initialize message
    @message = message.strip
  end

  def find_response
    if @message.empty?
      'Fine. Be that way!'
    elsif contains_no_lowercase_letters?
      'Woah, chill out!'
    elsif ends_with_a_question_mark?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def contains_no_lowercase_letters?
    @message.index(/[a-z]/).nil?
  end

  def ends_with_a_question_mark?
    @message.end_with? '?'
  end
end

class Bob
  def hey message
    MessageProcessor.new(message).find_response
  end
end
