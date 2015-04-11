class DNA
  def initialize(dna) @dna = dna end

  def hamming_distance other
    other = DNA.new other

    aligned = bases.zip(other.bases)
              .reject { |x| x.compact.size == 1 }

    found = aligned.group_by(&EQUAL)[false]
    found ? found.count : 0
  end
  EQUAL = proc { |x, y| x == y }

  def bases
    chars
  end

  # unsafe delegator:
  def method_missing(*a) @dna.send *a end
end
