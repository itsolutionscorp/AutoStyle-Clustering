class Series
  attr_accessor :strand

  def initialize strand
    @strand = strand
  end

  def slices n
    arg = []
    fail ArgumentError if n > strand.length
    strand.chars.map(&:to_i).each_cons(n){ |i| arg << i }
    arg
  end
end
