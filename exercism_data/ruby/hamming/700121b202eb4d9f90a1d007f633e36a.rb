class Hamming

  def self.compute(stranda, strandb)
    count = 0
    self.smallest_strand_size(stranda, strandb).times do |index|
      count += 1 unless stranda.chars.to_a[index] == strandb.chars.to_a[index]
    end
    count
  end

  private

  def self.smallest_strand_size(stranda, strandb)
    return strandb.length if stranda.length > strandb.length
    stranda.length
  end
end
