# Class bob
class Bob
  def hey(says)
    if silent?(says)
      'Fine. Be that way!'
    elsif screaming(says)
      'Woah, chill out!'
    elsif question(says)
      'Sure.'
    else
      'Whatever.'
    end
  end
  
  def silent?(says)
    true unless says.strip != ''
  end

  def screaming(says)
    true unless says.upcase! != nil || !says.match("[A-Z]")
  end

  def question(says)
    true unless !says.end_with? '?'
  end
end
