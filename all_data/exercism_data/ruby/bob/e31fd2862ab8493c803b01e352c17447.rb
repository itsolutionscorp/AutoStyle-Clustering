class Bob
  def hey(sentence)
  	last_char = sentence.chars.last
  	if sentence.strip.empty?
  	  'Fine. Be that way!'
  	elsif sentence.eql? sentence.upcase
  	  'Woah, chill out!'
  	elsif last_char.eql? '?'
  	  'Sure.'
  	else
  	  'Whatever.'
  	end
  end
end
