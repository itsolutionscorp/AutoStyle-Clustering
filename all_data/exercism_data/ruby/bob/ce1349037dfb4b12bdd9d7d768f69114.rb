class Bob
  def hey(msg)
    if msg.nil? || msg.empty?
      'Fine. Be that way!'
    elsif talking_forcefully(msg)
      'Whatever.'
    elsif msg.end_with?('!') || msg == msg.upcase || forceful_question(msg)
      'Woah, chill out!'
    elsif msg.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def talking_forcefully(string)
    return false if string.nil?
    string.start_with?("Let's")
  end

  def forceful_question(string)
    return false if string.nil?
    string[0..-2] == string[0..-2].upcase
  end

end
