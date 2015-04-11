class Bob
	def hey(expr)
		return 'Woah, chill out!' if shouted? expr
		return 'Sure.' if question? expr
		return 'Fine. Be that way!' if silent? expr
		'Whatever.'
	end

	def shouted?(expr)
		expr.upcase == expr && /[A-Z]/ =~ expr
	end

	def question?(expr)
		expr[-1] == '?'
	end

	def silent?(expr)
		expr.strip.empty?
	end
end
