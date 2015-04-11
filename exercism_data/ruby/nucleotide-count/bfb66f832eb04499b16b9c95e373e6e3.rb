class Nucleotide
  NUCLEOTIDES = %w{A T C G U}

  attr_reader :string

  def initialize(string)
    raise ArgumentError unless valid_nucleotide_string?(string)
    @string = string
  end

  def count(char)
    raise ArgumentError unless valid_nucleotide?(char)
    string.count(char)
  end

  def nucleotide_counts
    valid_nucleotides.each_with_object({}) do |char, hash| 
      hash[char] = count(char)
    end
  end

  private
  def valid_nucleotide_string?(string)
    string.chars.all?{|char| valid_nucleotides.include? char } 
  end

  def valid_nucleotide?(char)
    NUCLEOTIDES.include? char
  end
end

class DNA < Nucleotide
  def valid_nucleotides
    %w{A T C G}
  end
end

class RNA < Nucleotide
  def valid_nucleotides
    %w{A C G U}
  end
end
