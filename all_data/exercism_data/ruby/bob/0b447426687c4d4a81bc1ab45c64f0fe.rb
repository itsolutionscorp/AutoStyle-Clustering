class Bob
  def hey(message)
    if message
      message.gsub!(/\s+/, ' ')
      if message != '' && message != ' '
        if message.upcase == message
          "Woah, chill out!"
        elsif message.end_with?('?')
          "Sure."
        else
          "Whatever."
        end
      else
        'Fine. Be that way!'
      end
    else
      'Fine. Be that way!'
    end
  end
end
