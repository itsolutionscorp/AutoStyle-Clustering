class Bob
  def hey(msg)
    if msg.nil? || msg.empty?
      'Fine. Be that way!'
    elsif talking_forcefully(msg)
      'Whatever.'
    elsif msg.end_with?('!') || all_capital_characters?(msg) || forceful_question(msg)
      'Woah, chill out!'
    elsif msg.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def all_capital_characters?(string)
    return false if string.nil?
    string.chars.all? { |c| c == ' ' || ('A'..'Z').include?(c) }
  end

  def talking_forcefully(string)
    return false if string.nil?
    string.start_with?("Let's")
  end

  def forceful_question(string)
    return false if string.nil?
    all_capital_characters?(string[0..-2])
  end
end
