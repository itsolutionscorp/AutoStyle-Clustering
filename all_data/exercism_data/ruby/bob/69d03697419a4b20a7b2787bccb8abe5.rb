class Bob 
  def hey (talking)
    if talking == nil || talking.strip == '' 
      'Fine. Be that way!'
    elsif talking.upcase == talking && talking != ''
      'Woah, chill out!'
    elsif talking.end_with?("?")
      'Sure.'
    else
      'Whatever.'
    end
  end
end
