class Bob
  def hey(msg)
    case analyze(msg)
    when :silence    then 'Fine. Be that way!'
    when :shout      then 'Woah, chill out!'
    when :question   then 'Sure.'
    when :who_cares? then 'Whatever.'
    end
  end

  def analyze(msg)
    case
    when msg.to_s.strip == '' then :silence
    when msg == msg.upcase    then :shout
    when msg.end_with?("?")   then :question
    else                           :who_cares?
    end
  end
end
