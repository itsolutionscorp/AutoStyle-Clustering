class Hamming
  def self.compute(a, b)
    count = 0
    a.split('').each_with_index do |x, i|
      if x != b[i]
        count += 1
      end
    end
    count
  end
end
