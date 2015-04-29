class Bob
  def hey(input)
    input.strip!

    if input.empty?
      output = 'Fine. Be that way.'
    elsif input == input.upcase
      output = 'Woah, chill out!'
    elsif input.end_with?('?')
      output = 'Sure.'
    else 
      output = 'Whatever.'
    end

    return output
  end
end
