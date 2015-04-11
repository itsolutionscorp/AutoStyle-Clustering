class Hamming
  def self.compute(a,b)
    Strands.difference(
      Strand.parse(a),
      Strand.parse(b)
    ).count
  end
end

class Strands
  def self.difference(*strands)
    self.new(strands).difference
  end

  def initialize(strands)
    self.collection = strands
  end

  def difference
    combined.select {|strand_set| strand_set.first != strand_set.last}
  end

  private

  attr_accessor :collection

  def combined
    sorted[0].zip(sorted[1])
  end

  def sorted
    @sorted ||= collection.sort_by { |strand| strand.length }
  end
end

class Strand
  include Enumerable

  def self.parse(strand_string)
    self.new(strand_string.split(//))
  end

  def initialize(strand_string)
    self.collection = Array(strand_string)
  end

  def each(&block)
    collection.each(&block)
  end

  def length
    collection.count
  end

  private

  attr_accessor :collection
end
