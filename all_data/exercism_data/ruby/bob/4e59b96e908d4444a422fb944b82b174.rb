class Bob
  def hey text
    output = ''
    if !text || text.length == 0
      output = 'Fine. Be that way!'
    elsif text == text.upcase
      output = 'Woah. Chill out.'
    elsif text[-1] == "."
      output = 'Whatever.'
    elsif text[-1] == "?"
      output = 'Sure.'
    end

    output
  end
end
