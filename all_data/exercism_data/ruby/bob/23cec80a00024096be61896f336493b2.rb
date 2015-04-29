class Bob
  def hey(input)
    if input.empty? || input.rstrip.empty?
      'Fine. Be that way!'
    elsif input.upcase == input
      'Woah, chill out!'
    elsif input.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
