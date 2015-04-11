class Hamming
  def initialize(*strands)
    @min_length = strands.map(&:length).min
    @strands = strands
  end
  
  def compute
    @min_length.times.reduce(0) do |count, n|
      @strands[0][n] != @strands[1][n] ? count += 1 : count
    end
  end
  
  def self.compute(*args)
    new(*args).compute
  end
end
