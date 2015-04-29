module Hamming
  RUBY_VERSION_PRE_2 = Gem::Version.new(RUBY_VERSION) < Gem::Version.new('2.0.0')

  def self.compute(string1, string2)
    sequences = [string1, string2].map(&:chars)
    sequences = sequences.map(&:to_a) if RUBY_VERSION_PRE_2
    hamming_difference_of_array_of_sequences(sequences)
  end

  def self.hamming_difference_of_array_of_sequences(sequences)
    first, *rest = *(sequences.sort_by { |list| list.size })
    list_of_tuples = first.zip(*rest)
    list_of_tuples.map{ |tuple| hamming_difference_for_tuple(tuple) }.inject(:+) || 0
  end

  private
  def self.hamming_difference_for_tuple(tuple)
      elements_are_all_identical?(tuple) ? 0 : 1
  end

  def self.elements_are_all_identical?(tuple)
    tuple.uniq.size == 1
  end
end
