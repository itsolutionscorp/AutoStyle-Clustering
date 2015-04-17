class Hamming
  def self.compute(strandA, strandB)

    length = [strandA, strandB].min.length - 1
    data = strandA.split('')[0..length].zip(strandB.split('')[0..length])

    differences = data.collect do |point|
      1 if point[0] != point[1]
    end

    differences.compact.reduce(0,:+)
  end

end