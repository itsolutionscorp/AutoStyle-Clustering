class Bob
  
  def hey(param)
    param = param.to_s
    if param.empty?
      'Fine. Be that way.'
    elsif param == param.upcase
      'Woah, chill out!'
    elsif param.end_with? '?'
      'Sure.'
    else
      'Whatever.'
    end
      
  end
end
