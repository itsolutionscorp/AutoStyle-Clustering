class Bob
  def hey(statement)
    case
      when talking?(statement) then "Fine. Be that way."
      when shouting?(statement) then "Woah, chill out!"
      when asking?(statement) then "Sure."
      else "Whatever."
    end
  end

  private

  def talking?(statement)
    statement.to_s.empty?
  end

  def shouting?(statement)
    statement == statement.upcase
  end

  def asking?(statement)
    statement.end_with?("?")
  end
end
