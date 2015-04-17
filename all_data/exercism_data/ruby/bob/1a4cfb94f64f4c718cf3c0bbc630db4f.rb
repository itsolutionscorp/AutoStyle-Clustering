class Person
  def hey(msg)
    if msg.nil? || msg.empty?
      response_to_silence
    elsif msg == msg.upcase
      response_to_yelling
    elsif msg.end_with? '?'
      response_to_question
    else
      default_response
    end
  end
end

class Bob < Person
  def response_to_silence
    'Fine. Be that way!'
  end
  
  def response_to_yelling
    'Woah, chill out!'
  end

  def response_to_question
    'Sure.'
  end

  def default_response
    'Whatever.'
  end
end