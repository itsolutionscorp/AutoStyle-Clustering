class Hamming
  def self.compute(arg1, arg2)
    arg1.chars.zip(arg2.chars).count do |a, b|
      a != b
    end
  end
end
