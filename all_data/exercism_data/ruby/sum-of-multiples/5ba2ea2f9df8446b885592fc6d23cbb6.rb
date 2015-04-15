class SumOfMultiples
  attr_accessor :one, :two
  def initialize(one = 3, two = 5)
    @one, @two = one, two
  end

  def self.to(num)
    return 0 if num < 3
    ans = []
    (1...num).map do |x|
      if x % 3 == 0 || x % 5 == 0
        ans << x
      end
    end
    ans.inject(:+)
  end


  def to(num)
    ans = []
    (1...num).map do |x|
      if x % one == 0 || x % two == 0
        ans << x
      end
    end
    ans.inject(:+)
  end
end
