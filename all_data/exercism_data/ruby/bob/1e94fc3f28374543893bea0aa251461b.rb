class Bob
  def hey(some_small_talk)
    chat = Chat.new(some_small_talk)
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
  def initialize(small_talk)
    @small_talk = small_talk.to_s
  end

  def silent?
    @small_talk.empty?
  end

  def yelling?
    @small_talk == @small_talk.upcase
  end

  def question?
    @small_talk.end_with?('?')
  end
end
