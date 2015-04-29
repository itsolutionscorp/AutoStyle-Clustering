class Bob

  SHOUTING = lambda { |msg| msg == msg.upcase && !msg.strip.empty? }
  QUESTION = lambda { |msg| msg.end_with?('?') }
  SILENT = lambda { |msg| msg.strip.empty? }

  def hey(msg)
    case msg
    when SHOUTING then 'Woah, chill out!'
    when QUESTION then 'Sure.'
    when SILENT then 'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
