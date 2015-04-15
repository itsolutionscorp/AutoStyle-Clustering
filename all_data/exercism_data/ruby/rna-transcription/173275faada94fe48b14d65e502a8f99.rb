module RNA
  module Nucleotides
    class Adenine < Nucleotide
      def self.to_s
        'A'
      end

      def self.complement
        DNA::Nucleotides::Thymidine
      end
    end
  end
end
