class Bob
  def hey(message)
    case message
    when told_nothing
      'Fine. Be that way.'
    when yelled_at
      'Woah, chill out!'
    when asked_a_question
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def yelled_at
    ->(message) { message == message.upcase }
  end

  def asked_a_question
    ->(message) { message.end_with? '?' }
  end

  def told_nothing
    -> (message) { message.to_s.empty? }
  end
end
