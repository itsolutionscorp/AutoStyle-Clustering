class Luhn
  def initialize(num)
    @arr = num.to_s.split('').map { |c| c.to_i }
  end

  def addends
    @arr.reverse!.each_with_index do |digit, index|
      @arr[index] = digit * 2 if index.odd?
      @arr[index] = @arr[index] - 9 if @arr[index] >= 10
    end
    @arr.reverse!
  end

  def checksum
    sum = 0
    self.addends.each { |d| sum += d }
    return sum
  end

  def valid?
    self.checksum % 10 == 0
  end

  def self.create(num)
    luhn = Luhn.new(num)
    if luhn.valid?
      num
    else
      (num * 10) + ((10 - Luhn.new(num * 10).checksum) % 10) # oh god it's so ugly what have I done?
    end
  end
end
