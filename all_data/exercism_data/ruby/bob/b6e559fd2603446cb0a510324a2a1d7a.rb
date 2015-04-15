class Bob

  def hey words
    case 
    when words =~ /[[:alpha:]]/ && words.upcase! == nil
      "Woah, chill out!"
    when words =~ /\?\z/
      "Sure."
    when words.empty? || words =~ /\A\s+\z/
      "Fine. Be that way!"
    else
      "Whatever."
     end
  end

end
