module DNA
  module Nucleotides
    class Guanine < Nucleotide
      def self.to_s
        'G'
      end

      def self.complement
        RNA::Nucleotides::Cytosine
      end
    end
  end
end
