class Bob
  def hey s
    case s
    when /\?\Z/ then
      "Sure."
    when /\A([A-Z]|[^a-z])+\Z/ then
      "Woah, chill out!"
    when /./ then
      "Whatever."
    else
      "Fine. Be that way."
    end
  end
end
