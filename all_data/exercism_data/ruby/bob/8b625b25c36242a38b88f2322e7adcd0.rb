class Bob

  def hey(input)
    if input.strip == ''                    # no characters?
      'Fine. Be that way!'
    elsif input == input.upcase             # all caps?
      'Woah, chill out!'
    elsif last_character_of(input) == '?'   # question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  def last_character_of(input)
    input.reverse[0]
  end

end
