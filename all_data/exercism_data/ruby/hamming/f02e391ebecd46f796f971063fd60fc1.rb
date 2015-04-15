module Hamming
  class << self
    attr_reader :dna1, :dna2
        
    def compute dna1, dna2
      @dna1, @dna2 = dna1, dna2
      sortAttributes
      hammingDistance
    end
    
    private
      :dna1
      :dna2

      def sortAttributes
        if @dna1.length > @dna2.length
          @dna1, @dna2 = @dna2, @dna1
        end
      end

      def hammingDistance
        @dna1.each_char.zip(@dna2.each_char).select { |a, b| a != b }.size
      end
  end
end 
