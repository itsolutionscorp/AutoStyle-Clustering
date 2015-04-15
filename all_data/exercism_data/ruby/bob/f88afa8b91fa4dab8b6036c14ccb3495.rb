class Bob
  def hey message
    BobResponse.new(message).call
  end
end

class BobResponse < Struct.new(:message)
  def call
    if silent?
      "Fine. Be that way."
    elsif shouting?
      "Woah, chill out!"
    elsif question?
      "Sure."
    else
      "Whatever."
    end
  end

  def shouting?
    message == message.upcase
  end

  def question?
    message.end_with?("?")
  end

  def silent?
    message.to_s.strip == ''
  end
end
