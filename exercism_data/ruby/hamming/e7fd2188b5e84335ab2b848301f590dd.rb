module Hamming
  def self.compute(string_a, string_b)
    HammingComputer.new(string_a, string_b).distance
  end

  class HammingComputer
    def initialize(str_a, str_b)
      @string_a, @string_b = [str_a, str_b].sort_by(&:length)
    end

    def distance
      character_pairs.count {|x,y| x != y }
    end

    private

      attr_reader :string_a, :string_b

      def character_pairs
        string_a.chars.zip(string_b.chars)
      end
  end
end
