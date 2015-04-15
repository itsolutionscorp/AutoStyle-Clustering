class Acronym

	def self.abbreviate (phrase)
		acronymbase = phrase.split(/\s?\W+/)
		acronym = ""
		acronymbase.each {|x| acronym << x[0].to_s.upcase.strip }
		acronym
	end
end
