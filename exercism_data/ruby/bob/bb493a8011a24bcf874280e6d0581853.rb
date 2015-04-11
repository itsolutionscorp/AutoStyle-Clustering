class Bob
  def silence?(saywhat)
    return saywhat.strip.empty?
  end
  def yelling?(saywhat)
    return saywhat == saywhat.upcase()
  end
  def question?(saywhat)
    return saywhat.end_with?("?")
  end

  def hey(saywhat)
    puts "in bob::hey(): saywhat=#{saywhat}"
    case 
    when silence?(saywhat)
      return "Fine. Be that way!"
    when yelling?(saywhat)
      return "Woah, chill out!"
    when question?(saywhat)
      return "Sure."
    else
      return "Whatever."
    end
  end
end
