module RNA
  module Nucleotides
    class Nucleotide
      def self.to_s
        raise NotImplementedError
      end

      def self.complement
        raise NotImplementedError
      end
    end
  end
end
