class Bob
  def hey(question)
    case
    when (question =~ /^([^a-z]+)$/) && (question =~ /([A-Z]+)/)
      "Woah, chill out!"
    when question =~ /^(.+)\?\z/
      "Sure."
    when question =~ /^(\s*)\z/
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
