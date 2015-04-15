class SumOfMultiples
  def initialize(*args)
    @args = args
  end

  def to(n)
    (0...n).each_with_object([]) do |num, result|
      @args.each do |arg|
        result << num if num.modulo(arg).zero? and not(result.include?(num))
      end
    end.reduce(:+)
  end

  def self.to(n)
    new(3,5).to(n)
  end
end
