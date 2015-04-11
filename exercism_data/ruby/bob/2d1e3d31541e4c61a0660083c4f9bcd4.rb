class Bob

  def hey message
    case (message || "")
      when ""             then "Fine. Be that way!"
      when message.upcase then "Woah, chill out!"
      when /\?$/          then "Sure."
    else
      "Whatever."
    end
  end

end
