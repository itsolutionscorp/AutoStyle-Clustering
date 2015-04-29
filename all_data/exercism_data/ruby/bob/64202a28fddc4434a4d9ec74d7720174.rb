class Bob
  SILENCE = /^\s*$/
  YELL = /^[^a-z]+$/
  QUESTION = /^.*\?$/

  def hey(message = nil)
    case message
    when SILENCE
      'Fine. Be that way!'
    when YELL
      'Woah, chill out!'
    when QUESTION
      'Sure.'
    else
      'Whatever.'
    end
  end
end
