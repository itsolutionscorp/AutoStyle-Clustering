class Hamming
  def self.compute(first_strand, second_strand)
    dna_pair = DNAPair.new(first_strand, second_strand)
    dna_pair.count(&:mutation?)
  end
end

class DNAPair
  include Enumerable

  def initialize(first, second)
    @first  = first
    @second = second
  end

  def each
    0.upto shortest_length - 1 do |index|
      yield(BasePair.new(@first[index], @second[index]))
    end
  end

  private

  def shortest_length
    [@first, @second].min_by(&:length).length
  end
end

class BasePair
  def initialize(first, second)
    @first  = first
    @second = second
  end

  def mutation?
    @first != @second
  end
end