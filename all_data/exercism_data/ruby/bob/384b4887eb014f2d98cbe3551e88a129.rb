class Bob
  def hey(message)
    categorize_annoyance_type(message.to_s)
  end

  private
  def giving_silent_treatment?(message)
    message.strip.empty?
  end

  def screaming?(message)
    message == message.upcase
  end

  def questioning?(message)
    message =~ /\?$/ 
  end

  def categorize_annoyance_type(message)
    responses = {
      giving_silent_treatment?: 'Fine. Be that way!',
      screaming?: 'Woah, chill out!',
      questioning?: 'Sure.'
    }

    response = responses.select{|check,value| send(check, message)}
    return "Whatever." if response.empty?

    response.values.first
  end
end
