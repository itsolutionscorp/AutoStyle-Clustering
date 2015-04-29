class Hamming
  def self.compute(arg1, arg2)
    raise ArgumentError, "Sequences must have the same length" unless arg1.length == arg2.length
    differences = 0
    (0...arg1.length).each do |index|
      differences += 1 unless arg1[index] == arg2[index]
    end
    differences
  end
end
