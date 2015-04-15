#Would case statements be more efficient?
#Should I export the responses into variables?
class Bob
  def hey message
    message = message.to_s.strip
    if message == message.upcase && message != ''
      'Woah, chill out!'
    elsif message[-1] == '?'
      'Sure.'
    elsif message == ''
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
