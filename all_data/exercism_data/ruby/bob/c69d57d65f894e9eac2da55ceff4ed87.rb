# Indented using 2 spaces

class Bob

	def hey input	
    if silent(input)
      'Fine. Be that way.'
    elsif yelling(input)
  		'Woah, chill out!'
    elsif questioning(input)
  		'Sure.'
    else
  		'Whatever.'
		end
	end

  def silent input
    return true if input == '' || input == nil
  end

  def yelling input
    return true if input == input.upcase
  end

  def questioning input
    return true if input.split('').last == '?'
  end

end
