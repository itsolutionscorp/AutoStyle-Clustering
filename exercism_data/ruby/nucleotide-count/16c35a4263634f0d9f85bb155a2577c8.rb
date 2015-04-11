class DNA
  ELEMENTS = %w{A T C G U}

  def initialize(elements)
    raise ArgumentError unless valid?(elements)
    @elements = elements.split('')
  end

  def valid?(elements)
    elements.upcase == elements &&
      elements != 'ACGU'
  end

  def count(element)
    raise ArgumentError unless ELEMENTS.include?(element)
    @elements.count(element)
  end

  def nucleotide_counts
    nucleotide = {}
    ELEMENTS.each do |element|
      next if element == 'U'
      nucleotide[element] = count(element)
    end
    nucleotide
  end

end
