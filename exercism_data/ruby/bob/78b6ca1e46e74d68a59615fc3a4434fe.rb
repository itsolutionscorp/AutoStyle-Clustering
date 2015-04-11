class Bob

  def hey sentence
    msg_decoding = MessageDecoding.new(sentence)

    if msg_decoding.question?
      'Sure.'
    elsif msg_decoding.shouting?
      'Woah, chill out!'
    elsif msg_decoding.silent?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

end


class MessageDecoding

  def initialize(sentence)
    @message = sentence
  end

  def silent?
    @message = @message.gsub(/\s+/, "")
    @message.empty?
  end

  def shouting?
    @message == @message.upcase && !numeric? && !silent?
  end

  def question?
    (@message.end_with? "?") && !shouting?
  end

  private
    def numeric?
      @message.tr!('^A-Za-z0-9', '')
      true if Integer(@message) rescue false
    end

end
