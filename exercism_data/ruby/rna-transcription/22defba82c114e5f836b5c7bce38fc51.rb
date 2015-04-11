class Complement
  DNA_MAPPING_HASH = { 'G'=> 'C', 'C'=> 'G', 'A'=>'U', 'T'=> 'A' }
  RNA_MAPPING_HASH = { 'G'=> 'C', 'C'=> 'G', 'A'=>'T', 'U'=> 'A' }
  class << self
    def of_dna(nucleotides)
      convert(DNA_MAPPING_HASH, nucleotides)
    end

    def of_rna(nucleotides)
      convert(RNA_MAPPING_HASH, nucleotides)
    end

    private 
      def convert(conversion_hash, a)
        a.split('').map { |strand| conversion_hash.has_key?(strand) ? conversion_hash[strand] : raise(ArgumentError.new) }.join
      end
  end
end
