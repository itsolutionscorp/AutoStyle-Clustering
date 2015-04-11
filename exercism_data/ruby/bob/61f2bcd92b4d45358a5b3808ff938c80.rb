class Bob


  def hey(input)
    if input == ("") or input == ("    ")

      "Fine. Be that way!"
    elsif
      input == input.upcase
      'Woah, chill out!'
    elsif input.end_with? ("?") or input.include?("0..9")
      "Sure."
    elsif input.end_with? ("!")
      "Whatever."
    else
    # if input == 'Tom-ay-to, tom-aaaah-to.'
    'Whatever.'
    end

  end

end
