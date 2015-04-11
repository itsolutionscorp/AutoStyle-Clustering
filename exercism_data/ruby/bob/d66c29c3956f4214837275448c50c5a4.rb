class Bob
  def hey(listen)
    @heard = listen.delete ' '
    respond
  end

  def respond
    case
    when heard_nothing?
      'Fine. Be that way!'
    when heard_yelling?
      'Woah, chill out!'
    when heard_question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  def heard_nothing?
    @heard.empty?
  end

  def heard_yelling?
    @heard.upcase == @heard && @heard.match(/[A-Z]/)
  end

  def heard_question?
    @heard.end_with?('?') unless heard_yelling?
  end
end
