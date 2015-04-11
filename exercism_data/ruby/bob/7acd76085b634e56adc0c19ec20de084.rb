class Bob

  SILENT_RESPONSE = "Fine. Be that way."
  QUESTION_RESPONSE = "Sure."
  SHOUT_RESPONSE = "Woah, chill out!"
  CHAT_RESPONSE = "Whatever."
  
  def silent?(heard)
    heard.empty?
  end

  def question?(heard)
    heard.end_with?('?')
  end

  def shout?(heard)
    heard == heard.upcase
  end

  def hey(message)
    heard = message.to_s
    case 
    when silent?(heard) then SILENT_RESPONSE
    when question?(heard) then QUESTION_RESPONSE
    when shout?(heard) then SHOUT_RESPONSE
    else CHAT_RESPONSE
    end
  end
end
