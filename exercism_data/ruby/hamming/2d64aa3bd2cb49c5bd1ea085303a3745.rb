# Hamming code class?
class Hamming
  def self.compute( left, right )
    return 0 if left == right

    mutations = 0

    left.each_char.with_index do |lc, idx|
      break if idx == right.size
      mutations += 1 if lc != right[idx]
    end

    mutations
  end
end
