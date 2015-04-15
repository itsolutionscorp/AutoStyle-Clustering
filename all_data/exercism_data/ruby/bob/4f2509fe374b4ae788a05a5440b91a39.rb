class Bob
  def hey(anything)
  	
  	if anything.strip.empty?
      'Fine. Be that way!'
  	elsif anything == anything.upcase
  		'Woah, chill out!'
    elsif  anything.end_with?('?') 
      'Sure.'
  	else
  	  	"Whatever."
  	end
  end
end
