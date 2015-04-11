class Bob
  def hey(whats_up)
    chat = Chat.new(whats_up)
    respond_to(chat)
  end

  def respond_to(chat)
    if chat.silent?
      'Fine. Be that way.'
    elsif chat.yelling?
      'Woah, chill out!'
    elsif chat.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Chat
  def initialize(whats_up)
    @whats_up = whats_up.to_s
  end

  def silent?
    @whats_up.empty?
  end

  def yelling?
    @whats_up == @whats_up.upcase
  end

  def question?
    @whats_up.end_with?('?')
  end
end
