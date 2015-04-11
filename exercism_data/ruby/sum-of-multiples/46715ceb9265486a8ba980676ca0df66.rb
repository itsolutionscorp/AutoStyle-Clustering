class SumOfMultiples

  def initialize *multiples
    @multiples = multiples
  end

  def self.to max_amount
    som = new 3, 5

    som.to max_amount
  end

  def to max_amount
    sum = 0

    (1...max_amount).each do |number|
      if is_multiple?(number) == true
        sum += number
      end
    end
    sum
  end

  def is_multiple? number
    @multiples.any? {|multiple| number % multiple == 0}
  end
end
