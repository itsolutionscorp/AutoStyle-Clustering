class Bob
  def hey(input)
    if input.strip.empty?
      'Fine. Be that way!'
    elsif input.match(/[a-zA-Z]+/) && input.upcase == input
      'Woah, chill out!'
    elsif input[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
