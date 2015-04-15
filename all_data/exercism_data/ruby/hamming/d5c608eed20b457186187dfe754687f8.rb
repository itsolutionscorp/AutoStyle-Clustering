module Hamming
  class << self
    def compute(first, second)
      check_sequences!(first, second)

      first.length.times.reduce(0) do |differences, i|
        first[i] != second[i] ? differences + 1 : differences
      end
    end

    private

    def check_sequences!(first, second)
      if first.length != second.length
        raise ArgumentError.new('Sequences are not the same length')
      end
    end
  end
end
