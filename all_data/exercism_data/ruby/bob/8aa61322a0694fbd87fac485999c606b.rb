class Bob

  def hey(s)
    s=s.split("\n").join("")

    if is_yell?(s)
      "Woah, chill out!"
    elsif is_question?(s)
      "Sure."
    elsif is_nothing?(s)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private

  def is_yell?(s)
    s.gsub(/[^A-Za-z]/,"") =~ /^[A-Z]+$/
  end

  def is_question?(s)
    s =~ /\?$/
  end

  def is_nothing?(s)
    s =~ /^[ ]*$/
  end

end
