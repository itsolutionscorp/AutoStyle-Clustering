class Hamming

  def self.compute(original, copy)
    count = 0
    if original != copy
      [original.length, copy.length].min.times do |i|
        count += 1 if original[i] != copy[i]
      end
    end
    count
  end

end
