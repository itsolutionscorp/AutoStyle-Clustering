# Class bob
class Bob
  def hey(says)
    case 
      when silent?(says)
        'Fine. Be that way!'
      when  screaming?(says)
        'Woah, chill out!'
      when question?(says)
        'Sure.'
      else
        'Whatever.'
    end
  end
  
  def silent?(says)
    says.strip.empty?
  end

  def screaming?(says)
    says.eql? says.upcase and says.match("[A-Z]")
  end

  def question?(says)
    says.strip.end_with? '?'
  end
end
