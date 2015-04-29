class SumOfMultiples
  def initialize(*args)
    @divs = args.length > 0 ? args : [3, 5]
  end

  def to(n)
    1.upto(n - 1).inject([]) do |res, x|
      res << x if @divs.any? { |a| x % a == 0 }
      res
    end .inject(0, :+)
  end

  def self.to(n)
    s = SumOfMultiples.new.to(n)
  end
end
