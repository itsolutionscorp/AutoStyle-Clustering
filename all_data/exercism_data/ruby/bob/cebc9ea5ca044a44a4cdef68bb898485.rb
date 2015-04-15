class Bob
  def hey message
    if message.upcase == message
      'Woah, chill out!'
    elsif message.end_with? "?"
      'Sure.'
    elsif message.gsub(" ", "").empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
