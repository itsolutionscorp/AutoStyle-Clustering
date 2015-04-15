# Utilities for working with Hamming distance
class Hamming
  class << self
    # Count the number of differences between two DNA sequences of equal length.
    #
    # @param a [String]
    # @param b [String]
    # @return [Integer]
    def compute(a, b)
      sequences = [a, b]

      fail 'Sequence lengths must match.' unless same?(sequences.map(&:length))

      multiplex(sequences.map(&:each_char)).count(&method(:different?))
    end

    private

    # Whether any items are different
    #
    # @see #same?
    def different?(items)
      !same?(items)
    end

    # Multiplex enumerables into a single enumerator that yields from all at
    # once as an array, stopping when any enumerable stops iterating.
    #
    # @param enumerables [Array<Enumerable>]
    # @return [Enumerator]
    def multiplex(enumerables)
      Enumerator.new do |yielder|
        loop do
          yielder.yield enumerables.map(&:next)
        end
      end
    end

    # Whether all items are the same
    #
    # @param items [Array]
    # @return [Boolean]
    def same?(items)
      items.uniq.length == 1
    end
  end
end
