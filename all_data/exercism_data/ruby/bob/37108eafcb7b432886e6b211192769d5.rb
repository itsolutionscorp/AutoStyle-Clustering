class Bob

  def is_silent(line)
    line.match(/\A\s*\z/)
  end

  def is_upper_case(line)
    line.upcase! == nil
  end

  def is_question(line)
    line[-1] == "?"
  end


  def hey(line)
    if is_silent(line)
      "Fine. Be that way!"
    elsif is_upper_case(line)
      "Woah, chill out!"
     elsif is_question(line)
      "Sure."
    else
      "Whatever."
    end
  end

 
end
