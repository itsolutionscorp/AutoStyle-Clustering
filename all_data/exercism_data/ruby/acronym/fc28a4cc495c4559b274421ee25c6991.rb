class Acronym

	def self.abbreviate(phrase)
		phrase.split(/\s|[-]/).map {|word| word.scan(/(^[a-zA-z])|[a-z]([A-Z])/)}.join.upcase
	end
end
