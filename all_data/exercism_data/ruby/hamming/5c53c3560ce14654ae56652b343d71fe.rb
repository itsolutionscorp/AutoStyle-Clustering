class Hamming
  def self.compute(strand1, strand2)
    if strand1.length != strand2.length
      raise "Strands must be of equal length"
    end

    arr1 = strand1.split("")
    arr2 = strand2.split("")
    tuples = arr1.zip(arr2)

    sum = 0

    tuples.each do |tuple|
      if tuple[0] != tuple[1]
        sum += 1
      end
    end

    sum
  end
end
