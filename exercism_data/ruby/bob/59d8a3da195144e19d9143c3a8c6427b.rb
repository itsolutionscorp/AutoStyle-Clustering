class Bob 
  def hey (talking)
    if talking.to_s.strip == '' 
      'Fine. Be that way!'
    elsif talking.upcase == talking 
      'Woah, chill out!'
    elsif talking.end_with?("?")
      'Sure.'
    else
      'Whatever.'
    end
  end
end
