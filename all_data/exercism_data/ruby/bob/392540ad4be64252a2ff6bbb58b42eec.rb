class Bob
  def hey(message)
    silence = /\A\s*\Z/
    shouting = /(?=.*[A-Z])(\A[^a-z]*\Z)/
    question = /\?\Z/

    case message
    when silence
      'Fine. Be that way!'
    when shouting
      'Woah, chill out!'
    when question
      'Sure.'
    else
      'Whatever.'
    end
  end
end
