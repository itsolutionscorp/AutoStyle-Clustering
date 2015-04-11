class Hamming
  def self.compute(strand_a, strand_b)
    difference = 0

    strand_a = strand_a[0, strand_b.length] if strand_a.length > strand_b.length

    strand_a.chars.zip(strand_b.chars).each do |left, right|
      difference +=1 unless left == right
    end

    difference
  end
end
