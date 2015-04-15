class Bob
  def hey(arg)
    case arg
    when '', nil
      return "Fine. Be that way."
    when /^[A-Z][a-z\s-]+['?,][a-z\s-]+[.!]$/
      return "Whatever."
    when /[A-Z\s]*[1-9,\W\s]*[A-Z\s]+[1!]*$/
      return "Woah, chill out!"
    when /[A-Z][a-z\s]+[?]$/
      return "Sure."
    else
    end
  end
end
