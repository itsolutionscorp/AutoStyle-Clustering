module RNA
  module Nucleotides
    class Uracil < Nucleotide
      def self.to_s
        'U'
      end

      def self.complement
        DNA::Nucleotides::Adenine
      end
    end
  end
end
