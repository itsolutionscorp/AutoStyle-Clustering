class Bob

  def initialize
    @message = ""
  end

  def hey(message)
    @message = replace_new_lines(message)
    if is_upper?
      shouting
    elsif is_question?
      question
    elsif is_silence?
      silence
    else
      normal
    end
  end

  private

  def replace_new_lines(message)
    message.gsub(/(\r\n|\n|\r)/,"")
  end

  def shouting
    'Woah, chill out!'
  end

  def normal
    'Whatever.'
  end

  def question
    'Sure.'
  end

  def silence
    'Fine. Be that way!'
  end

  def is_upper?
    if has_letters?
      @message == @message.upcase
    end
  end

  def is_question?
    if @message.length >= 1
      @message[-1, 1].include? '?'
    end
  end

  def has_letters?
    /[a-zA-Z]/.match(@message)
  end

  def is_silence?
    @message.empty? or /^\s*$/.match(@message)
  end

end
