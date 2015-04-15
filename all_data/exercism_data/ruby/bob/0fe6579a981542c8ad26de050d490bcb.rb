class Bob
  def hey(input_message)
    case input_message.gsub("\n", "")
    when /^\s*$/
      "Fine. Be that way!"
    when /^[\d\W]*[A-Z]+[A-Z\d\W]*$/
      "Woah, chill out!"
    when /\?$/
      "Sure."
    else
      "Whatever."
    end
  end
end
