require 'set'
class SumOfMultiples 

  attr_reader :multiple_array
  def initialize(*multiple_array)
    @multiple_array = multiple_array
  end

  def to(upto)
    SumOfMultiples.to(upto, multiple_array)
  end

  class << self

    def to(upto, multiples_array = [3, 5])
      s = Set.new.add(0)
      multiples_array.each do |multiple|
        multiple.step(upto-1, multiple).each { |n| s.add(n) } 
      end
      s.inject(:+)
    end

  end

end
