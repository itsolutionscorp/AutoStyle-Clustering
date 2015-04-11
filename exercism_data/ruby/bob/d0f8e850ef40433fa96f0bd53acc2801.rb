# This passes, really curious why the prior iteration didn't.

class Bob

	def hey input
		
    if input.nil? || input == ''
      'Fine. Be that way.'
    elsif input == input.upcase
  		'Woah, chill out!'
    elsif input.split('').last == '?'
  		'Sure.'
    else
  		'Whatever.'
		end
	end

end
