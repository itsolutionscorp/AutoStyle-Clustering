class Hamming

  def self.compute( base_dna, mutated_dna )
    return Dna.new(base_dna, mutated_dna).difference.hamming_difference
  end

  private
    
    class Dna      
      attr_reader :hamming_difference
      
      def initialize( base_dna, mutated_dna )
        @base_dna     = base_dna
        @mutated_dna  = mutated_dna
        @hamming_difference = 0
        self
      end

      def difference
        (0..( [@base_dna.length, @mutated_dna.length].min - 1 )).each do |i|
          @hamming_difference += ( @base_dna[i] <=> @mutated_dna[i] ).abs
        end
        self
      end

      # def difference
      #   (0..( [@base_dna.length, @mutated_dna.length].min - 1 )).each { |i| @hamming_difference += ( @base_dna[i] <=> @mutated_dna[i] ).abs }
      #   self
      # end
    end
end
