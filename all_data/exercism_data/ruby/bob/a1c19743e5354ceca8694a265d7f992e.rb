class Bob
  def hey(say)
  	if say.gsub(/[\s]/, '') == ''
  	  'Fine. Be that way!'
  	elsif say.include?('!') && say.swapcase == say.downcase || say.downcase == say.swapcase.gsub(/[0-9]/, '')
  	  'Whoa, chill out!'
  	elsif say[-1,1].include?('?')
  	  'Sure.'
  	else
  	  'Whatever.'
  	end
  end
end
