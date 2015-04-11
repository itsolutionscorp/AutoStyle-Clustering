class Bob

  def hey(input)
    if input.strip.empty?            # no characters?
      'Fine. Be that way!'
    elsif input == input.upcase      # all caps?
      'Woah, chill out!'
    elsif input[-1] == '?'           # question?
      'Sure.'
    else
      'Whatever.'
    end
  end

end
