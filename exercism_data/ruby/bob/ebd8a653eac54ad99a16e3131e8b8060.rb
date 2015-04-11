class Bob
	module Response
		Whatever='Whatever.'
		Woah='Woah, chill out!'
		Sure='Sure.'
		Fine='Fine. Be that way!'
	end
	module Hearing
		Question=/(\A.*[?]\Z)/
		Loud=/(\A[^a-z]+\Z)/
		Symbols=/(\A[^a-zA-Z]+\Z)/
		Silence=/(\A[[:space:]]+\Z)|(\A\Z)/
	end
	def hey(s)
		input=s.chomp
		case input 
		when Hearing::Silence then Response::Fine
		when Hearing::Question
			case input
			when Hearing::Symbols then Response::Sure
			when Hearing::Loud then Response::Woah
			else Response::Sure
			end
		when Hearing::Symbols then Response::Whatever
		when Hearing::Loud then Response::Woah
		else Response::Whatever
		end
	end
end
