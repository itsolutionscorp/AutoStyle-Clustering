class Bob

  def hey(comment)
    case 
      when silence?(comment)
      "Fine. Be that way!"
      when shouting?(comment)
      'Woah, chill out!'
      when question?(comment)
      "Sure."
      else
      "Whatever."
      end
  end

  def silence?(comment)
    comment.strip == ''
  end

  def shouting?(comment)
    comment == comment.upcase && comment != comment.downcase
  end

  def question?(comment)
    comment.end_with? '?' 
  end

end
