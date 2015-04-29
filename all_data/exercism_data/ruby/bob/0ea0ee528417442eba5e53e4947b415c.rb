class Bob
  def hey(str)
    return silence_reply if str.nil? or str.strip.empty?
    return shouting_reply if str == str.upcase
    return question_reply if str.end_with? '?'
    'Whatever.'
  end
  
  def silence_reply
    'Fine. Be that way!' 
  end

  def shouting_reply
    'Woah, chill out!' 
  end

  def question_reply
    'Sure.'
  end
end
