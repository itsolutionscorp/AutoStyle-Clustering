class Bob
  def hey(input)
    if input.nil? || input.length == 0
      'Fine. Be that way.'
    elsif input[-1,1] == '?'
      'Sure.'
    elsif input == input.upcase
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end
end
