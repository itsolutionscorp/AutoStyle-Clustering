class Bob
  def hey(message)
    case message
      when nil
        "Fine. Be that way!"
      when /^\s*$/
        "Fine. Be that way!"
      when /^[^a-z]+$/
        "Woah, chill out!"
      when /[a-z].*\?$/
        "Sure."
      else
        "Whatever."
    end
  end
end
