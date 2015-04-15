class Bob

  def hey(voiced)
    if voiced.to_s.strip.length == 0
      'Fine. Be that way!'
    elsif voiced == voiced.upcase
      'Woah, chill out!'
    elsif voiced[-1,1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end

end
