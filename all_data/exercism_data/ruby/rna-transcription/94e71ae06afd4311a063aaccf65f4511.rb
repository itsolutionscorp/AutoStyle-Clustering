module DNA
  module Nucleotides
    class Thymidine < Nucleotide
      def self.to_s
        'T'
      end

      def self.complement
        RNA::Nucleotides::Adenine
      end
    end
  end
end
