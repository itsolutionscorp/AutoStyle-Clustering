class SumOfMultiples
  def initialize *multiples
    @multiples = multiples 
  end
  def to limit
    (1...limit).inject(0) do |sum, num|
      self.class.is_multiple?(num, @multiples) ? sum + num : sum
    end
  end

  def self.to limit
    (1...limit).inject(0) do |sum, num|
      is_multiple?(num) ? sum + num : sum
    end
  end

  private

  def self.is_multiple? num, multiples = [3, 5]
    multiples.each { |multiple| return true if num % multiple == 0 }
    false
  end
end
