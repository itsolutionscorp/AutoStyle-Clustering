class Bob
  def hey(heard)
    case
    when silence(heard) then "Fine. Be that way!"
    when shouting(heard) then "Woah, chill out!"
    when question(heard) then "Sure."
    else "Whatever."
    end
  end

  private

  def silence(heard)
    heard.nil? || heard.strip == ""
  end

  def shouting(heard)
    heard == heard.upcase
  end

  def question(heard)
    heard.end_with?("?")
  end
end
