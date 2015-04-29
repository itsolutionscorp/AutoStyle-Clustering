class Bob

  def hey(statement)
    case statement
    when /^.+[a-z].+\?$/ # Questioning
      'Sure.'
    when /^([^\sa-z]+\s?)+$/ # Yelling
      'Woah, chill out!'
    when /^.*[a-z]+.*[^\?]$/ # Telling
      "Whatever."
    else # Everything else
      "Fine. Be that way!"
    end
  end

end
