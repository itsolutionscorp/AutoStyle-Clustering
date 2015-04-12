class Hamming
  def compute(strandA, strandB)
    nucleids_a = strandA.chars
    nucleids_b = strandB.chars
    nucleids_a.zip(nucleids_b).count do |(a, b)|
      a && b && a != b
    end
  end
end
