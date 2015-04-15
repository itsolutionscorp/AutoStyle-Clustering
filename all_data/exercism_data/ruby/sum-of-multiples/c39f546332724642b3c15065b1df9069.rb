require 'pry'
class SumOfMultiples



  def self.to(num)
    sum = 0
    (3...num).each do |n|
      if n%3== 0 || n%5==0
        sum += n
      end
    end
    sum
  end

  def initialize(*num)
    @num = num
  end

  def to(bound)
    sum = 0
    (0...bound).each do |n|
      @num.each do |xo|
        if n%xo == 0
          sum += n
        end
      end
    end
    sum
  end
end
