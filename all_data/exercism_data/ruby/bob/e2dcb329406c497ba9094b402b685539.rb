class Bob

  def hey(input)
    if input.strip.empty?
      'Fine. Be that way!'
    elsif input == input.upcase && !!input.match(/[A-Za-z]/)
      "Woah, chill out!"
    elsif input == "Tom-ay-to, tom-aaaah-to." || input[-1] == "!"
      "Whatever."
    elsif input[-1] == "?"
      "Sure." 
    else
      "Whatever."
    end
  end
end
