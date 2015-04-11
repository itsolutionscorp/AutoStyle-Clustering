class DNA

	attr_reader :sequence, :dictionary

	def initialize sequence, dictionary=DnaDictionary.new, container=DnaContainer.new
		@dictionary = dictionary
		container.store sequence
		@sequence = container
	end

	def to_rna
		sequence.map_letter do |letter| 
			dictionary.translate_letter(letter)
		end.to_s
	end
end

class DnaContainer

	attr_accessor :sequence, :splitter, :converted

	def initialize splitter=StringSplitter
		@splitter = splitter
	end

	def store sequence
		@sequence = sequence
	end

	def map_letter
		@converted = splitter.split(sequence).map do |chunk|
			yield chunk
		end
		self
	end

	def to_s
		splitter.join(converted).to_s
	end
end

module StringSplitter
	def self.split string
		string.split('')
	end

	def self.join string
		string.join
	end
end

class DnaDictionary
	DICTIONARY = {"C" => "C", "A" => "A", "G" => "G", "T" => "U"}

	def translate_letter letter
		DICTIONARY[letter]
	end
end
