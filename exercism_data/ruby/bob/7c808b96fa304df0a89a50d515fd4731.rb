class Message
  def initialize message
    @message = message
  end

  def type
    case
    when empty?
      :empty
    when yell?
      :yell
    when question?
      :question
    else
      :normal
    end
  end

  private

  def empty?
    @message.strip.empty?
  end

  def question?
    @message.end_with?('?')
  end

  def yell?
    upcase? && !only_numbers?
  end

  def only_numbers?
    clean(@message).empty?
  end

  def upcase?
    @message == @message.upcase 
  end

  def clean(message)
    # string without numbers, coma and question mark
    message.gsub(/\d|\W/,'')
  end
end

class Bob
  class << self; attr_accessor :responses end

  @responses = {
    empty: "Fine. Be that way!",
    yell: "Woah, chill out!",
    question:"Sure.",
    normal:"Whatever."
  }

  def hey(message_heard)
    respond(Message.new(message_heard).type)
  end

  private

  def respond(response_type)
    Bob.responses[response_type]
  end
end
