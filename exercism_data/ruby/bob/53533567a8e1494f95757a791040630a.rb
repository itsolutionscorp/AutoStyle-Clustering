class Bob
  def hey message
    if message.gsub(" ", '').empty?
      'Fine. Be that way!'
    elsif message.upcase == message
      'Woah, chill out!'
    elsif message.split('').last == "?"
      'Sure.'
    else
      'Whatever.'
    end
  end

end
