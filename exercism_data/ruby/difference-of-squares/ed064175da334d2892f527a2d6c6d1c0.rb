class Squares

  @@instances_with_memos = {}

  @@methods = {
    square_of_sums: lambda { |n| (1..n).reduce(:+) ** 2 },
    sum_of_squares: lambda { |n| (1..n).map {|n| n **2} .reduce(:+)},
    difference: lambda do |n| 
      get_memo_or_compute(:square_of_sums, n) - get_memo_or_compute(:sum_of_squares, n) 
    end
  }

  attr :memos
  

  def initialize(count)
    @count = count
    @memos = {}

    #only add to this instance's @memos if no instance got here first
    @@instances_with_memos[count] || @@instances_with_memos[count] = self

  end

  # should only get called once Squares.new(count) has been called
  # so that there's already a value for @@instances_with_memos[count]
  def self.get_memo_or_compute(method, count)

    @@instances_with_memos[count] or raise "Squares object not instantiated"

    @@instances_with_memos[count].memos[method] ||
      @@instances_with_memos[count].memos[method] = @@methods[method].call(count)

  end


  def method_missing(method_name, *args, &block)
    if @@methods.has_key? method_name
      Squares.get_memo_or_compute(method_name, @count)
    else
      super
    end
  end

end
    
