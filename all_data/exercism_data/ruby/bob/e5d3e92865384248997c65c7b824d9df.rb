class Bob
  def hey(input)
    if input.nil? || input.length == 0
      'Fine. Be that way.'
    elsif input.end_with?('?')
      'Sure.'
    elsif input == input.upcase
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  private

end
