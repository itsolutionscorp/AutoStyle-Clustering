class Bob
  def hey(message)
    return "Sure."              if is_question? message
    return "Woah, chill out!"   if is_yelling? message
    return brospeak message     if is_bro? message
    return "Fine. Be that way." if message.empty?
    "Whatever."
  end
  
  private
  def is_question? message
    message[/\?\Z/]
  end

  def is_yelling? message
    message[/\A[^a-z]+\Z/]
  end

  def is_bro? message
    message[/\ABro, /]
  end

  def brospeak message
    leet strip_bro(message)
  end

  def strip_bro message
    message.match(/\ABro, (.+)/)[1]
  end

  def leet message
    translator = {
        /[oO]/ => '0',
        /[lL]/ => '1',
        /[zZ]/ => '2',
        /[eE]/ => '3',
        /[hH]/ => '4',
        /[sS]/ => '5',
        /[tT]/ => '7',
        /[bB]/ => '8',
        /[gG]/ => '9'
    }
    
    translator.each_pair { |pattern,value| message.gsub!(pattern, value) }
                  
    return message
  end
end
