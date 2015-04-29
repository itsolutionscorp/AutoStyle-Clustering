module DNA
  module Nucleotides
    class Cytosine < Nucleotide
      def self.to_s
        'C'
      end

      def self.complement
        RNA::Nucleotides::Guanine
      end
    end
  end
end
