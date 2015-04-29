class Bob
  def hey (input)
    output = 'Whatever.'
    if '' == input.strip
      output = 'Fine. Be that way!'
    elsif input =~ /[a-z]/i && input == input.upcase
      output = 'Woah, chill out!'
    elsif '?' == input[-1, 1]
      output = 'Sure.'
    end
    output
  end
end
