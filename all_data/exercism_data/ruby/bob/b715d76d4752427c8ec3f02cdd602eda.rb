class Bob

  def hey(input)
    input.strip!
    if input == input.upcase && input.match(/[a-zA-Z]+/)
      "Whoa, chill out!"
    elsif input.end_with?('?')
      "Sure."
    elsif input.empty?
      "Fine. Be that way!"
    else
    "Whatever."
    end
  end

end
