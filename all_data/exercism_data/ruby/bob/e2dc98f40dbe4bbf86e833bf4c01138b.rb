class Bob

  def hey(communication)
    responses[categorize_communication(communication)]
  end

  private

  def responses
    {
      silence:   'Fine. Be that way.',
      yelling:   'Woah, chill out!',
      question:  'Sure.',
      statement: 'Whatever.',
    }
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
