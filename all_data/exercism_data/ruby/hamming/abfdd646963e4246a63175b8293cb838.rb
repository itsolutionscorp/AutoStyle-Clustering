module Hamming
  extend self

  def compute(*args)
    strandA = args[0].chars
    strandB = args[1].chars
    normalize_strands(strandA, strandB)
    hamming_distance(strandA, strandB)
  end

  private
  def hamming_distance(a, b)
    comparision      = a.zip(b)
    results = []
    comparision.each do |el|
      el[0] == el[1] ? next : results.push(1)
    end
    results.length
  end

  def normalize_strands(a, b)
    strand_compare = a.size <=> b.size
    case strand_compare
      when 1
        diff = a.size - b.size
        a.pop(diff)
      when 0
        # do nothing
      when -1
        diff = b.size - a.size
        b.pop(diff)
      else
        fail "Don't know how to compare"
    end
  end

end
