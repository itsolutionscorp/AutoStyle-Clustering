class Bob
  def hey message
    what_i_heard = Listen.new message
    if what_i_heard.silence?
      "Fine. Be that way."
    elsif what_i_heard.shouting?
      "Woah, chill out!"
    elsif what_i_heard.asking?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Listen < Struct.new :message
  def shouting?
    message == message.upcase
  end

  def asking?
    message.end_with? "?"
  end

  def silence?
    message.nil? || message.empty?
  end
end
