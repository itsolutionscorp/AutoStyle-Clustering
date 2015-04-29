class Bob
  def hey(message)
    if message.nil? or message.strip.empty? then
      'Fine. Be that way!'
    elsif message.eql? message.upcase then
      'Woah, chill out!'
    elsif message.end_with? '?' then
      'Sure.'
    else
      'Whatever.'
    end
  end
end
