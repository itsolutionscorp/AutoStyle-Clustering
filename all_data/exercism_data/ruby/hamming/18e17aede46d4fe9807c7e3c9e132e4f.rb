# Utilities for working with Hamming distance
class Hamming
  # Count the number of differences between two DNA sequences of equal length.
  #
  # @param a [String]
  # @param b [String]
  # @return [Integer]
  def self.compute(a, b)
    Utilities.multiplex([a, b].map(&:each_char)).count(&:heterogeneous?)
  end
end

# General-purpose extensions to Enumerable
module Enumerable
  # Whether any members are different from each other
  #
  # @return [Boolean]
  def heterogeneous?
    first = self.first

    self.any? { |item| item != first }
  end
end

# General-purpose utilities
class Utilities
  # Multiplex enumerables into a single enumerator that iterates over all at
  # once, stopping when any stop.
  #
  # @param enumerables [Enumerable<Enumerable>]
  # @return [Enumerator]
  def self.multiplex(enumerables)
    Enumerator.new do |yielder|
      loop do
        yielder.yield enumerables.map(&:next)
      end
    end
  end
end
