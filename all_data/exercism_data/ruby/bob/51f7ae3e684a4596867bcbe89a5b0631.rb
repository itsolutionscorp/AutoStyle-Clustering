require 'pry'

class Bob
  def hey message
    reply_to(Message.new(message).kind)
  end

  private
  def reply_to kind
    replies = {
      empty: 'Fine. Be that way!',
      all_caps: 'Woah, chill out!',
      question: 'Sure.'
    }
    replies.fetch(kind, 'Whatever.')
  end
end

class Message
  def initialize message
    @message = message
  end

  def kind
    if is_empty?
      :empty
    elsif is_all_caps?
      :all_caps
    elsif is_question?
      :question
    end
  end

  private
  def is_all_caps?
    lowcase = @message.scan(/\p{Lower}/)
    upcase = @message.scan(/\p{Upper}/)
    lowcase.empty? and !upcase.empty?
  end

  def is_question?
    @message[-1] == '?'
  end

  def is_empty?
    @message.strip.empty?
  end
end
