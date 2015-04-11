class Hamming
  def self.compute original, mutation
    original.split('').each_with_index.inject(0) do |diff, (o, i)|
      diff += single_hamming_diff(o, mutation[i])
    end
  end

  private
  def self.single_hamming_diff(o, m)
    return 0 if m.nil?
    o == m ? 0 : 1
  end
end
