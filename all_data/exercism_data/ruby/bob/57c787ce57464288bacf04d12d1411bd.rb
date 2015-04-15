class Bob
  def hey(statement)
    if statement.blank?
      "Fine. Be that way!"
    elsif screaming?(statement)
      'Woah, chill out!'
    elsif statement[statement.size - 1] == "?"
      'Sure.'
    else
      "Whatever."
    end
  end

  private

  def screaming?(statement)
    statement == statement.upcase && /[a-zA-Z]/.match(statement) 
  end
end
