class Bob
  def hey(string)
    if (string == string.upcase) && (string =~ /[A-Z]/)
      "Woah, chill out!"
    elsif string.end_with?("?")
      "Sure."
    elsif string.strip.empty?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
=begin
class Bob

  def hey(message)
    case
      when (message == message.upcase) && (message =~ /[A-Z]/)
        "Woah, chill out!"
      when message.end_with?("?")
        "Sure."
      when message.empty?
        "Fine. Be that way!"
      when message.upcase.to_i.to_s ==message
        "Whatever."
      when message.include?("   ")
        "Fine. Be that way!"
      else
     "Whatever."
    end
  end
end
=end
