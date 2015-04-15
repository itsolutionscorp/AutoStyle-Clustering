# cuarto rubocop
class Hamming
  def self.compute(val1, val2)
    acum = 0
    j = 1
    for i in 0..val1.length
      if val1[i] != val2[i]
        acum = acum + 1
        j = j + 2
      end
    end
    acum
  end
end
