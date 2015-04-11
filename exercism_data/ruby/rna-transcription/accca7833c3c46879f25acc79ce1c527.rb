module RNA
  module Nucleotides
    class Cytosine < Nucleotide
      def self.to_s
        'C'
      end

      def self.complement
        DNA::Nucleotides::Guanine
      end
    end
  end
end
