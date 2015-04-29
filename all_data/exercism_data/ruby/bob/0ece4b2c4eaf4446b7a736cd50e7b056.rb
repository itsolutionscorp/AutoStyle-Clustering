class Bob
  def hey(message)
    message = Message.new(message)
    if message.silence?
      "Fine. Be that way!"
    elsif message.shouting? || message.forceful_question?
      "Woah, chill out!"
    elsif message.question?
      "Sure."
    else
      "Whatever."
    end
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
end
