class SumOfMultiples

  def initialize(*nums)
    @mods = nums
  end

  def self.to(num)
    SumOfMultiples.new(3,5).to(num)
  end

  def to(num)
    results = []
    (0...num).each { |i| @mods.each { |mod| results << i and break if i % mod == 0 } }
    sum(results)
  end

  def sum(array)
    array.empty? ? 0 : array.inject(:+)
  end

end
