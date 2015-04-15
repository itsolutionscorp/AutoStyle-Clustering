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
    (string <=> string.upcase) == 0
  end

  def question?(string)
    string[-1] == '?'
  end

  def silent?(string)
    string.nil? || string.squeeze(" ").strip.empty?
  end
end
