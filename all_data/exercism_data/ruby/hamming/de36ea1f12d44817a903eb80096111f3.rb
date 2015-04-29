class Hamming

    def self.compute(original_strand, mutated_strand)
        hamming_distance = 0

        zip_strands(original_strand, mutated_strand).each do |original, mutated|
            mutated and hamming_distance += 1 if original != mutated
        end

        hamming_distance
    end

    private

    def self.zip_strands(original_strand, mutated_strand)
        original_pieces = original_strand.chars
        mutated_pieces = mutated_strand.chars

        original_pieces.zip(mutated_pieces)
    end
end
