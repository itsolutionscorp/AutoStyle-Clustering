class Hamming
  def self.compute(base, desc)
    count = 0
    (0..base.length).each do |i|
      count += 1 unless base[i] == desc[i]
    end
    count
  end
end
