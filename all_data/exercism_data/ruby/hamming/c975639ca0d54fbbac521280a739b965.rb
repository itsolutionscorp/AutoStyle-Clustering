class Hamming
  def self.compute(first, second)
    Hamming.new(first, second).compute
  end

  def initialize(first, second)
    @first, @second = first, second
  end

  def compute
    total = 0
    strand1.each_with_index do |char, index|
      total += 1 unless char == strand2[index]
    end
    total
  end

  private

  def size
    @size = [@first.size, @second.size].min
  end

  def strand1
    @strand1 = @first.slice(0...size).scan(/./)
  end

  def strand2
    @strand2 = @second.slice(0...size).scan(/./)
  end
end
