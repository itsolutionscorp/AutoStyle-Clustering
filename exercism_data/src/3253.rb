class Hamming
  class << self
    def compute(first, second)
      first.chars.zip(second.chars).count do |a, b|
        a && b && a != b
      end
    end
  end
end
