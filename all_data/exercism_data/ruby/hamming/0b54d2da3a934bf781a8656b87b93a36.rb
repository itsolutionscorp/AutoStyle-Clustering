class Hamming
  def self.compute(a, b)
    mutations = 0

    comparable_size = [a.size, b.size].min

    comparable_size.times do |position|
      mutations += 1 if a[position] != b[position]
    end

    mutations
  end
end
