class Hamming

  class NotComparable < ArgumentError
    def initialize(message = 'DNA strands must be equal in length.')
      super
    end
  end

  def self.compute(first_strand, second_strand)
    raise NotComparable unless comparable?(first_strand, second_strand)

    first_strand.chars.each_with_index.count do |character, index|
      second_strand[index] != character
    end
  end

  private

  def self.comparable?(*strands)
    strands.all? do |strand|
      strand.length == strands.first.length
    end
  end

end
