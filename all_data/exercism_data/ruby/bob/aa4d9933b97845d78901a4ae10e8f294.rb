class Bob

  def hey(input)
    if input == ''
      "Fine. Be that way."
    else
      characters = []
      input.split('').each do |char|
        characters << char
      end
      if characters.last == '?'
        "Sure."
      elsif characters[1] == characters[1].upcase
        "Woah, chill out!"
      else
        "Whatever."
      end
    end
  end
end
