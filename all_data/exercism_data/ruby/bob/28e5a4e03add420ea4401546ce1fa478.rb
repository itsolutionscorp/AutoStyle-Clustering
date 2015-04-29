class Bob
  def hey(input)
    if not input.upcase! and input.match(/[A-Z]/)
      return "Whoa, chill out!"
    elsif input.end_with?('?')
      return "Sure."
    elsif not input.match(/\w+/)
      return "Fine. Be that way!"
    else
      return "Whatever."
    end
  end
end
