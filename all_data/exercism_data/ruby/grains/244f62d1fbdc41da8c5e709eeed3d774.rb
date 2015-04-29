class Grains
  def initialize

  end
  def square(number)
    2**(number-1)
  end
  def total
    1.upto(65).each do |number|
    @total = (0 + 2**(number-1))
    end
    @total - 1
  end
end
