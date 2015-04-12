class Hamming
  def compute(stand_a, stand_b)
    sample_a = stand_a.chars
    sample_b = stand_b.chars
    count = 0
    sample_a.count.times do |num|
      if sample_a.length <= sample_b.length && sample_a[num] != sample_b[num]
        count += 1
      end
    end
    count
  end
end
