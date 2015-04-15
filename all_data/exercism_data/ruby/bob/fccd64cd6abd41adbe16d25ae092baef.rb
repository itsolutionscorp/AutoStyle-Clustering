class Bob
  def hey( string )
    case
    when yelling( string )
      "Woah, chill out!"
    when question( string )
      "Sure."
    when silent( string )
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  def question( string )
    string.end_with?('?')
  end

  def silent( string )
    string.strip.empty?
  end

  def yelling( string )
    if contains_letters( string )
      string.upcase == string
    else
      false
    end
  end

  def contains_letters( string )
    if string =~ /[A-Za-z]+/
      true
    else
      false
    end
  end
end
