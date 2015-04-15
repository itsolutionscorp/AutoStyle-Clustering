class Bob
  def hey(msg)
    case msg
    when nil, '' then "Fine. Be that way."
    when /[A-Z]{2,}/ then "Woah, chill out!"
    when /\?$/ then "Sure."
    else "Whatever."
    end
  end
end
