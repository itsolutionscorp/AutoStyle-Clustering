class Bob
  def hey(line)
    # checks if line is empty or just has spaces
    if line == '' || line[0,1] == " "
      'Fine. Be that way!'
    # checks if it's a question, and not angry
    elsif line[-1] == '?' && (line != line.upcase || line.upcase == line.downcase)
      'Sure.'
    # checks if it's angry and contains letters
    elsif line == line.upcase && line != line.downcase
      'Woah, chill out!'
    # default
    else
      'Whatever.'
    end
  end
end
