class Bob
  def hey(message)
    case
      when message.eql?("") || message.squeeze.eql?(" ")
        then "Fine. Be that way!"
      when message.upcase.eql?(message)
        then "Woah, chill out!"
      when message[-1,1].eql?("?")
        then "Sure."
      else
        "Whatever."
    end
  end
end
