class Bob

  def hey(something)
    if yelling?(something)
      "Woah, chill out!"
    elsif question?(something)
      "Sure."
    elsif silence?(something)
      "Fine. Be that way!"
        else
      "Whatever."
    end
  end

  def yelling?(something)
    something == something.upcase && something =~ /[A-Z]/
  end

  def question?(something)
    something.end_with?("?")
  end

  def silence?(something)
    something.strip.empty?
  end

end
