class Bob
  def hey(statement)
    if silent?(statement)
      'Fine. Be that way!'
    elsif shouting?(statement)
      'Woah, chill out!'
    elsif question?(statement)
      'Sure.'
    else
      "Whatever."
    end
  end

  private

  def shouting?(string)
    string == string.upcase
  end

  def question?(string)
    string.end_with?('?')
  end

  def silent?(string)
    string.nil? || string.strip.empty?
  end
end
