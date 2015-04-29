module DNA
  module Nucleotides
    class Adenine < Nucleotide
      def self.to_s
        'A'
      end

      def self.complement
        RNA::Nucleotides::Uracil
      end
    end
  end
end
