class DNA

  attr_reader :string, :counts
  def initialize(string = '')
    raise ArgumentError unless dna_nucleotides?(string)
    @string = string
    buildcounts
  end

  def count(char='Z')
    raise ArgumentError unless nucleotide?(char)
    counts[char]
  end

  def nucleotide_counts
    counts
  end

private
  def initcounts
    @counts = Hash.new(0)
    dna_nucleotides.each {|n| @counts[n] = 0 }
  end

  def buildcounts
    initcounts
    string.chars.each {|ch| @counts[ch] += 1 }
  end

  def dna_nucleotides
    ['A', 'T', 'C', 'G']
  end

  def dna_nucleotide?(char)
    dna_nucleotides.include? char
  end

  def dna_nucleotides?(string)
    string.chars.inject(true) do |answer, c|
      answer && dna_nucleotide?(c)
    end
  end

  def nucleotide?(char)
    dna_nucleotide?(char) || (char == 'U')
  end

end
