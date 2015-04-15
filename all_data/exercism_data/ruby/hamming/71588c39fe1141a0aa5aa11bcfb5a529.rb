class Hamming
  def self.compute(first, last)
    new(first, last).compute
  end

  attr_reader :short, :long
  private :short, :long

  def initialize(first, last)
    @short, @long = [first.chars, last.chars].sort_by!(&:size)
  end

  def compute
    short.zip(long).count { |a,b| a != b }
  end
end
