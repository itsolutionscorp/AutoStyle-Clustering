require 'pry'

class DNA
  def initialize args
    @strands = [Strand.new(args)]
  end

  def hamming_distance args
    @strands << Strand.new(args)
    iterations = get_iteration_count
    hamming = Strand.compare(@strands, iterations)
  end

  private
  def get_iteration_count
    s = @strands.min_by {|s| s.strand.length}
    s.strand.length
  end
end

class Strand
  attr_reader :strand
  def initialize args
    @strand = args
  end

  def self.compare (strands, iterations)
    @strands = strands
    if @strands[0].strand.empty?
      return 0
    else
      hamming = 0
      iterations.times do |i|
        hamming += 1 unless strands[0].strand.chars[i] == strands[1].strand.chars[i]
      end
      hamming
    end
  end
  
end
