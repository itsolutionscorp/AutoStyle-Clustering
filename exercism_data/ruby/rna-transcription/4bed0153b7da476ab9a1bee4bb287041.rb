class Complement
	def self.compute (string)
		string.chars.gsub!('C','G').gsub!('G','C').gsub!('A','T').gsub!('U','A')
	end
end
