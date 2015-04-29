class Bob
  def hey(sentence)
  	case
  	when sentence.strip.empty?
  	  'Fine. Be that way!'
  	when (sentence.eql? sentence.upcase)
  	  'Woah, chill out!'
  	when (sentence.end_with? '?')
  	  'Sure.'
  	else
  	  'Whatever.'
  	end
  end
end
