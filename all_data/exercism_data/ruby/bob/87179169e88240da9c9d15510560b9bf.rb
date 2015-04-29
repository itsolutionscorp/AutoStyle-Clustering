class Bob

  def hey(communication)
    response(categorize_communication(communication))
  end

  private

  def response(communication_category)
    case communication_category
    when :silence
      'Fine. Be that way.'
    when :yelling
      'Woah, chill out!'
    when :question
      'Sure.'
    else
      'Whatever.'
    end
  end

  def categorize_communication(communication)
    if communication.to_s == ''
      :silence
    elsif communication.upcase == communication
      :yelling
    elsif communication.end_with? '?'
      :question
    else
      :statement
    end
  end

end
