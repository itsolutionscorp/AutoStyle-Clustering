class Bob

  def hey words
    if words =~ /[[:alpha:]]/ && words.upcase! == nil
       "Woah, chill out!"
    elsif words =~ /\?\z/
        "Sure."
    elsif words.empty? || words =~ /\A\s+\z/
        "Fine. Be that way!"
    else
       "Whatever."
     end
  end

end
