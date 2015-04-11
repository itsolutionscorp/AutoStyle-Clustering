module Hamming
  class << self
    def compute(first, second)
      check_sequences!(first, second)

      differences = 0

      first.length.times do |i|
        differences += 1 if bases_differ?(first[i], second[i])
      end

      differences
    end

    private

    def check_sequences!(first, second)
      if first.length != second.length
        raise ArgumentError.new('Sequences lenghts must be the same')
      end
    end

    def bases_differ?(first, second)
      first != second
    end
  end
end
