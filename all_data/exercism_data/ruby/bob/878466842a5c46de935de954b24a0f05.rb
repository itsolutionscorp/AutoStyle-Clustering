class Bob
  
  def hey(input)
    if empty_string?(input)  
      'Fine. Be that way!'
    elsif yelling?(input)
      "Woah, chill out!"
    elsif is_question?(input)
      "Sure."
    else  
      "Whatever."
    end
  end

  def is_question?(string)
    string.end_with? "?"
  end

  def yelling?(string)
    string == string.upcase && string =~ /[A-Z]/
  end

  def empty_string?(string)
    string !~ /\S/ ? true : false  
  end
end
