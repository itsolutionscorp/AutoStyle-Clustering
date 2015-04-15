class Bob
  SILENCE = /\A\s*\Z/
  SHOUTING = /\A[^a-z]+\Z/
  QUESTION = /\?\Z/

  def hey(msg)
    case msg
    when SILENCE
      'Fine. Be that way!'
    when SHOUTING
      'Woah, chill out!'
    when QUESTION
      'Sure.'
    else
      'Whatever.'
    end
  end
end
