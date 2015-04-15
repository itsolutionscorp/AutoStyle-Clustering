class Bob

  def hey message
    string_end_matches = message.end_with? '.',' ','!'
    return 'Whatever.' if string_end_matches && message.upcase! != nil
    string_end_matches = message.end_with? '?',' '
    return 'Sure.' if string_end_matches && message.upcase! != nil
    return 'Woah, chill out!' if message.upcase! == nil && message.strip != ""
    return 'Fine. Be that way.' if message.strip == ""

  end

end
