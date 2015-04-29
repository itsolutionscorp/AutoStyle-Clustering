class DNA
	attr_reader :nucleotide_counts

	def initialize(value)
		@nucleotide_counts = count_chain value
	end

	def count(value)
		throw ArgumentError if value=='X'
		@nucleotide_counts[value].to_i
	end

	private

	def count_chain(chain)
		temp = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
		begin
			chain.chars.each do |c|
				temp[c] += 1
			end
			temp
		rescue
			throw ArgumentError
		end
	end
end
