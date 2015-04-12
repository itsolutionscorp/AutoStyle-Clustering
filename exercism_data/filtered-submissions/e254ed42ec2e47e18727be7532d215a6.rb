class Hamming
  def compute(strand0, strand1)
    return 0 if strand0 == strand1
    strand0.each_char.zip(strand1.each_char).map do |x, y|
      !x.nil? && !y.nil? && x != y ? 1 : 0
    end.reduce :+
  end
end
