class Bob
  def hey(message)
    case
      when message.upcase == message && /[A-Z]/ =~ message
        then "Woah, chill out!"
      when message[-1] == "?"
        then "Sure."
      when message.strip.empty?
        then "Fine. Be that way!"
      else
        "Whatever."
    end
  end
end
