class Bob

  def hey message
    string_end_matches = message.strip.end_with? '?'
    return 'Fine. Be that way.' if message.strip == ""
    return 'Woah, chill out!' if message.upcase! == nil
    return 'Sure.' if string_end_matches
    return 'Whatever.' 
  end

end
