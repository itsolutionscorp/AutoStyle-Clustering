class Bob
  Yell = 'Woah, chill out!'
  Question = 'Sure.'
  Nothing = 'Fine. Be that way!'
  Anything = 'Whatever.'
  def hey(msg)
    if is_nothing(msg)
      Nothing
    elsif is_yelling(msg)
      Yell
    elsif is_question(msg)
      Question
    else
      Anything
    end
  end
  def is_question(msg)
    return msg[-1] == '?'
  end
  def is_yelling(msg)
    tmp = msg.gsub(/[^a-zA-Z]/,'')
    return (tmp != "" and tmp == tmp.upcase)
  end
  def is_nothing(msg)
    return msg.rstrip == "" 
  end
  private :is_question, :is_yelling, :is_nothing
end
