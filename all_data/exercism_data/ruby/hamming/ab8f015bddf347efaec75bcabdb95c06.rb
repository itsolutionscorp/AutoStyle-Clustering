class Hamming
  class << self
    def compute(strand_one, strand_two)
      length = get_length(strand_one.length, strand_two.length)
      get_difference(strand_one, strand_two, length)
    end

    def get_length(length_one, length_two)
      length_one > length_two ? length_two : length_one
    end

    def get_difference(strand_one, strand_two, length)
      diff = 0
      length.times {|i| diff += 1 if strand_one[i] != strand_two[i]}
      diff
    end
  end
end
