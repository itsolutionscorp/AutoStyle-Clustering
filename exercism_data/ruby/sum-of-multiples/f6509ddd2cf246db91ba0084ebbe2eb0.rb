class SumOfMultiples

  def initialize(*nums)
    @mods = nums
  end

  def self.to(num)
    results = []
    (1...num).each { |i| results << i if i % 3 == 0 || i % 5 == 0 }
    sum(results)
  end

  def to(num)
    results = []
    (1...num).each { |i| @mods.each { |mod| results << i if i % mod == 0 } }
    sum(results)
  end

  def sum(array)
    array.inject:+
  end

  def self.sum(array)
    array.empty? ? 0 : array.inject(:+)
  end

end
