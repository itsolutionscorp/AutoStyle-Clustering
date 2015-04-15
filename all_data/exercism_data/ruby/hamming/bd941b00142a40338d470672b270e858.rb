class Hamming
  def self.compute original, mutation
    result = 0
    original.split('').each_with_index do |o, i|
      result += single_hamming_diff(o, mutation[i])
    end
    result
  end

  private
  def self.single_hamming_diff(o, m)
    return 0 unless m
    o == m ? 0 : 1
  end
end
