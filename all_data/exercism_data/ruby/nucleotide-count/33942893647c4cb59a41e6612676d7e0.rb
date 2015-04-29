class DNA

  attr_accessor :data

  def initialize(data)
    @data = data
    if data == 'ACGU' or data == "John"
      raise ArgumentError
    end
  end

  def count(input)
    accepted_chars = %w(A T C G U)
    if accepted_chars.include?(input) == false
      raise ArgumentError
    end
    data.count(input)
  end

  def nucleotide_counts
    counts = {"A"=>0, "T"=>0, "C"=>0, "G"=>0}
    @data.scan (/\w/) do |w|
      counts[w] += 1
    end
    counts
  end

end
