class Hamming

  def self.compute(a, b)

    hamming = 0
    (0..shortest_length(a,b) - 1).each do |i|
      hamming += 1 unless a[i] == b[i]
    end

    hamming
  end

  private

  def self.shortest_length(a,b)
    a.size < b.size ? a.size : b.size
  end

end


# shorter but wrong:
# class Hamming
#
#   def self.compute(a, b)
#     [a, b].min.size.times.count { |i| a[i] != b[i] }
#   end
#
# end
