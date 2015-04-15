class Bob

  def hey(input)
    if input.strip.empty?
      'Fine. Be that way!'
    elsif input == input.upcase && !!input.match(/[A-Za-z]/)
      "Woah, chill out!"
    elsif input[-1] == "?"
      "Sure." 
    else
      "Whatever."
    end
  end
end
