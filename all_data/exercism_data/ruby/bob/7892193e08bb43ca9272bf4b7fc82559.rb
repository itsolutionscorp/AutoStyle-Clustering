# Class bob
class Bob
  def hey(says)
    if silent?(says)
      'Fine. Be that way!'
    elsif screaming?(says)
      'Woah, chill out!'
    elsif question?(says)
      'Sure.'
    else
      'Whatever.'
    end
  end
  
  def silent?(says)
    says.strip.eql? ''
  end

  def screaming?(says)
    says.upcase!.eql? nil and says.match("[A-Z]{1}")
  end

  def question?(says)
    says.strip.end_with? '?'
  end
end
