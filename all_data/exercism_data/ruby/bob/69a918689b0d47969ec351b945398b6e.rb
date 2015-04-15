class Bob

  def hey(message)
    @conversation = Conversation.new(message)
    respond_message
  end

  def respond_message
    return 'Woah, chill out!' if @conversation.is_a_yelling?
    return 'Sure.' if @conversation.is_a_question?
    return 'Fine. Be that way!' if @conversation.is_saying_nothing?
    return 'Whatever.' if @conversation.anything_else?
  end
end

class Conversation

  def initialize(message)
    @message = message
  end

  def is_a_question?
    !!(@message =~ /\?$/) && !has_multiple_line?
  end

  def is_a_yelling?
    @message == @message.upcase &&
      !!@message.match('[a-zA-Z]')
  end

  def is_saying_nothing?
    message_without_spaces = @message.gsub(' ', '')
    message_without_spaces == ''
  end

  def anything_else?
    !is_a_question? && !is_a_yelling? && !is_saying_nothing?
  end

  def has_multiple_line?
    !!(@message.match'\n')
  end
end
