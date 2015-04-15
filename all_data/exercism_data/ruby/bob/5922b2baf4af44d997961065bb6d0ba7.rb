class Bob
  def hey input
    case input.to_s
    when /^\s*$/
      "Fine. Be that way!"
    when input.upcase
      "Woah, chill out!"
    when /.*\?$/
      "Sure."
    else
      "Whatever."
    end
  end
end
