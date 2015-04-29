class Hamming
  def self.compute(strand1,strand2)
    result = 0
    strand1.split('').each_with_index do |v,i|
      if v != strand2[i]
        result += 1
      end
    end
    return result
  end
end
