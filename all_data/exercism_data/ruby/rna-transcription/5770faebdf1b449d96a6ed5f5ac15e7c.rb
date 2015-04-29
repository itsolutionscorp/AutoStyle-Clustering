module RNA
  module Nucleotides
    class Guanine < Nucleotide
      def self.to_s
        'G'
      end

      def self.complement
        DNA::Nucleotides::Cytosine
      end
    end
  end
end
