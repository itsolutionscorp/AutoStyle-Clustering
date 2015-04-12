class Hamming
  def compute(strand0, strand1)
    return 0 if strand0 == strand1
    strand0.chars.zip(strand1.chars).count do |char0, char1|
      !char1.nil? && char0 != char1
    end
  end
end
