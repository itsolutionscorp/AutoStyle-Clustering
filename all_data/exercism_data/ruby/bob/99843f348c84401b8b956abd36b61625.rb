class Bob
  def hey(input)
    if input.empty?
      return 'Fine. Be that way.'
    elsif input == input.upcase
      return 'Woah, chill out!'
    elsif input.end_with?('?')
      return 'Sure.'
    else
      return 'Whatever.'
    end
  end
end
