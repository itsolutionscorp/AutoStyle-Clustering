class DNA

	def initialize sequence
		@sequence = sequence
	end

	def to_rna
		each_letter(@sequence) do |base|
			transcribe base
		end
	end

	private

	def each_letter phrase, &block
		phrase.split('').map do |letter|
			yield letter
		end.join
	end

	def transcribe base
		is_it_DNA_base? base
		base.tr 'ACGT', 'ACGU'
	end

	def is_it_DNA_base? base
		raise 'NotDNABase' unless %w{A T G C}.include? base
	end
end
