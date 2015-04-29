# encoding: UTF-8
class Bob
	def hey(statement)
		return "Woah, chill out!" if yelling?(statement)
		return "Sure." if asking?(statement)
		return "Fine. Be that way!" if silent?(statement)
		return "Whatever."
	end

	def asking?(statement)
		statement =~ /\?\Z/
	end

	def yelling?(statement)
		statement.upcase == statement and has_letters?(statement)
	end

	def silent?(statement)
		statement.strip == ""
	end

	def has_letters?(statement)
		statement =~ /[\p{Lu}]/
	end
end
