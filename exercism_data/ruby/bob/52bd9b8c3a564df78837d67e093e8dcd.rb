class Bob
  def hey(argument)
    case
    when argument.strip.empty?
      "Fine. Be that way!"
    when argument =~ /[A-Z]/ && argument.upcase == argument
      "Woah, chill out!"
    when argument.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end
end
