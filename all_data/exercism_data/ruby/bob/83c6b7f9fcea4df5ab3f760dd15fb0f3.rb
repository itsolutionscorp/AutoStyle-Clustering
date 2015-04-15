class Bob

  def hey(message)
    message.strip!
    return "Fine. Be that way!" if message.empty?
    unless message.upcase.eql?(message) && message.match(/[A-Z]/)
      message[-1].eql?("?") ? (return "Sure.") : (return "Whatever.")
    end
    "Woah, chill out!"
  end
end
