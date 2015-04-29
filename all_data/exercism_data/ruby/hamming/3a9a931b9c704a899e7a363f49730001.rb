require 'pry'

class Hamming
  class << self
    attr_reader :first, :second

    def compute(first_strand, second_strand)
      @first = first_strand.chars
      @second = second_strand.chars
      compare_strands
    end

    def compare_strands
      pairs = second.zip(first)
      pairs.delete_if { |pair| pair.include?(nil)}
      pairs.select {|array| array.uniq.count == 2}.count
    end
  end

end
