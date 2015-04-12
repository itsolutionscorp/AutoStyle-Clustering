class Hamming
  def compute(base, compare)
    diff = 0

    # (0..(base.length - 1)) is a Range object, so for a base of length five the object will be [0, 1, 2, 3, 4]
    # Algorithm iteratres through the Range object and compares both base and compare at the same index
    # This works under the assumption that both base and compare are of the same length
    (0..(base.length - 1)).each do |index|
      if base[index] != compare[index]
        diff += 1
      end
    end
    diff
  end

end
