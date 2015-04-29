class DNA

	attr_reader :dna_sequence, :dictionary

	def initialize sequence, dictionary=DnaDictionary.new, container=DnaContainer.new
		@dictionary = dictionary
		container.add sequence
		@dna_sequence = container
	end

	def to_rna
		dna_sequence.map_letter do |letter| 
			dictionary.translate_letter(letter)
		end.join
	end
end

class DnaContainer

	attr_accessor :sequence, :splitter, :splitted

	def initialize splitter=StringSplitter
		@splitter = splitter
	end

	def add sequence
		@sequence = sequence
	end

	def map_letter
		@splitted = splitter.split(sequence).map do |chunk|
			yield chunk
		end
		self
	end

	def join
		splitter.join(splitted)
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
