class Bob

  def hey phrase
    case phrase.to_s
    when yelled?
      "Woah, chill out!"
    when /\?\z/
      "Sure."
    when ''
      "Fine. Be that way!"
    else 
      "Whatever."
    end
  end

  private
  def yelled? words
    /[A-Z]/ =~ words && /[a-z]/ !~ words
  end

end
