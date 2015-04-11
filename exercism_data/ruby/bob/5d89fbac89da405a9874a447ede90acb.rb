class Bob
  def hey(msg)
    msg.strip!
    case 
      when nothing?(msg) then "Fine. Be that way!"
      when yelling?(msg) then "Woah, chill out!"
      when question?(msg) then "Sure."
      else "Whatever."
    end
  end

  private 

  def question?(m)
    m =~ /\?\z/
  end

  def yelling?(m)
    m =~ /[A-Z\W\d%^*@#$()!?]*/ && 
      m =~ /[A-Z]/ && 
      !(m =~ /[a-z]/)
  end

  def nothing?(m)
    m =~ /^$/
  end

end
