class Bob
  def hey(greeting)
    case
    when silence?(greeting)
      "Fine. Be that way!"
    when yelling?(greeting)
      "Woah, chill out!"
    when question?(greeting)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def silence?(greeting)
    greeting.nil? or greeting.empty?
  end

  def yelling?(greeting)
    greeting == greeting.upcase
  end

  def question?(greeting)
    greeting.end_with?("?")
  end
end
