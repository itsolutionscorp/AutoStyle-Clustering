class Hamming
  class << self
    def compute(strand_one, strand_two)
      strand_one.chars.each_with_index.inject(0) do |memo, (element, index)|
        memo += score(element, strand_two.slice(index))
      end
    end

    private

    def score(strand_one, strand_two)
      return 0 if strand_one == strand_two
      1
    end
  end
end
