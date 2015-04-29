class Hamming
  def self.compute(one, two)
    min_length = [one.length, two.length].min

    count = 0
    (0..min_length - 1).each do |i|
      count = count + 1 if one[i] != two[i]
    end

    count
  end
end
