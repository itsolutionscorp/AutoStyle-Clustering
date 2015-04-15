class Hamming
  def self.compute(one, two)
    count = 0

    common_length(one, two).times do |i|
      count += 1 if one[i] != two[i]
    end
    count
  end

  private
  def self.common_length(one, two)
    [one.length, two.length].min
  end
end
