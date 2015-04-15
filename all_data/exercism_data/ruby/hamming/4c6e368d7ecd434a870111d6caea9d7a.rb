class Hamming
  def self.compute a, b
    count = 0
    a.length.times do |i|
      count += 1 if a[i] != b[i]
    end
    count
  end
  # def self.compute a, b
  #   count = 0
  #   a.split('').zip(b.split('')).each do |c, d|
  #     count = count + 1 if c != d
  #   end
  #   count
  # end
end
