class Bob
  def hey statement


    if statement.strip.empty?
      'Fine. Be that way!'
    elsif statement == statement.upcase && statement =~ /[a-zA-Z]/
        "Whoa, chill out!"
    elsif statement.end_with?("?")
        "Sure."
    else
      "Whatever."
    end

  end

end
