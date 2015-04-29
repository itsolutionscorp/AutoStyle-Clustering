class Hamming

  class << self

    def compute(original, other)
      original, other = other, original if original.length > other.length
      count_differences(original, other)
    end

    private

      def count_differences(original, other)
        difference = 0
        original.chars.each_with_index do |c, i|
          difference += 1 unless other[i].eql?(c)
        end
        difference
      end

  end

end
