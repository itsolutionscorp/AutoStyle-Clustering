class Squares

  @@instances = {}

  attr :memos, :count

  def initialize(count)
    @count = count
    @@instances[count] || @@instances[count] = self
    @memos = {}
  end

  @@methods = {
    square_of_sums: lambda { |n| (1..n).reduce(:+) ** 2 },
    sum_of_squares: lambda { |n| (1..n).map {|n| n **2} .reduce(:+)},
    difference: lambda { |n| @@instances[n].square_of_sums(n) - @@instances[n].sum_of_squares(n) }
  }
  
  def self.get_memo_or_compute(method, count)
    @@instances[count].memos[method] ||
      @@instances[count].memos[method] = @@methods[method].call(count)
  end

    
  def instances
    @@instances
  end

  def method_missing(method_name, *args, &block)
    if @@methods.has_key? method_name
      Squares.get_memo_or_compute(method_name, @count)
    else
      super
    end
  end

end
    
