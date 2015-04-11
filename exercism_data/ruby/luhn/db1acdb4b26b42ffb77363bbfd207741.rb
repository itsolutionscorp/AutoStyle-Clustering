class Luhn
  def initialize(num, divisor = :odd?)
    @num = num
    @divisor = divisor
  end

  def addends
    strs = @num.to_s.chars.map(&:to_i)
    strs.reverse.each_with_index.with_object([]) do |(val, i), results|
      if i.send(@divisor)
        results << ((val * 2 > 9) ? (val * 2 - 9) : val * 2)
      else
        results << val
      end
    end.reverse
  end

  def checksum
    addends.inject(:+)
  end

  def valid?
    checksum % 10 == 0
  end

  def self.create(num)
    luhn = Luhn.new(num, :even?)
    if luhn.valid?
      results = num.to_s << "0"
    else
      results = num.to_s << (10 - luhn.checksum.to_s[-1].to_i).to_s
    end
    results.to_i
  end
end
