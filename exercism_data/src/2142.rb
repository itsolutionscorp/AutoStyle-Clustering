class Hamming

  class << self
    def compute(a, b)
      pairs = a.chars.zip(b.chars).reject do |pair|
        pair[0].nil? || pair[1].nil?
      end
      pairs.count {|pair| pair[0] != pair[1]}
    end
  end

end
