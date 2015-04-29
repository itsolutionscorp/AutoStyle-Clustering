class Grains
  def initialize

  end
  def square(number)
    2**(number-1)
  end
  def total
    total = 0
    1.upto(65).each do |number|
    @new_total = (total + 2**(number-1))
    end
    @new_total - 1
  end
end
