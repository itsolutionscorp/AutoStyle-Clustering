class Hamming

  def self.compute(code1, code2)
    zipped_codes = code1.split("").zip(code2.split(""))
    zipped_codes.count   { |x| x[1] != nil && x[0] != x[1]}
  end
end
