class Bob

  def hey(msg)
    case
    when msg.to_s.strip.empty? then 'Fine. Be that way!'
    when msg.upcase.eql?(msg)  then 'Woah, chill out!'
    when msg.end_with?('?')    then 'Sure.'
    else                            'Whatever.'
    end
  end

end
