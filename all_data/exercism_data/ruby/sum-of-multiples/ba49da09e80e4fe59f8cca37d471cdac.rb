require 'pry-byebug'
require 'pry-stack_explorer'

class SumOfMultiples
  def initialize(*var)
    @multiples = var
  end
  
  def self.to(num)
    new.to(num)
  end

  def to(num)
    @num = num
    if @multiples == []
    list = (0...@num).to_a
    mult_3 = list.select {|x| x % 3 == 0}
    mult_5 = list.select {|x| x % 5 == 0}
    mult_3_5 = mult_3.concat(mult_5)
    mult_3_5.uniq!
    mult_3_5.inject {|acc, x| acc + x}
    else
     mult_not_3_or_5
    end
  end

  private

    def mult_not_3_or_5
      to_inject = []
      while @multiples.empty? == false
        list = (0...@num).to_a
        one_multiple = @multiples.pop
        mult = list.select { |x| x % one_multiple == 0}
        to_inject << mult
      end
     first_inject = to_inject.inject {|acc, x| acc + x}
     first_inject.uniq!
     second_inject = first_inject.inject {|acc, x| acc + x}
     second_inject
    end
end

SumOfMultiples.to(1)
