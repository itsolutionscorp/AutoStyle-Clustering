class DNA

  DNA = %w( A T C G )
  RNA = %w( A C G U )

  def initialize(str)
    raise ArgumentError unless valid_data?(str)
    @data = str
  end

  def count(char)
    raise ArgumentError unless valid_nucleotide?(char)
    @data.count(char)
  end

  def nucleotide_counts
    hsh = {}
    DNA.each { |n| hsh[n] = count(n) }
    hsh
  end

  def valid_nucleotide?(char)
    (DNA + RNA).include?(char)
  end

  def valid_data?(str)
    str.match /^(#{DNA.join('|')})*$/
  end

end
