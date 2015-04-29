class Bob
  def hey(message)
    message = Message.new(message)
    message.response
  end
end

class Message < Struct.new(:message)
  def shouting?
    upcased? && !only_numbers?
  end

  def upcased?
    (message.upcase == message)
  end

  def has_acronym?
    message.include? "DMV"
  end

  def question?
    message.end_with?("?")
  end

  def forceful_question?
    (upcased? && question?) unless only_numbers?
  end

  def only_numbers?
    message.split(",").map(&:strip).all? {|n| n.match(/\A\d\??\z/)}
  end

  def silence?
    message.strip.empty?
  end

  def response
    if silence?
      "Fine. Be that way!"
    elsif shouting? || forceful_question?
      "Woah, chill out!"
    elsif question?
      "Sure."
    else
      "Whatever."
    end
  end
end
