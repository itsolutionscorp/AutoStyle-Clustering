class SumOfMultiples

  def self.to(num)
    results = (1..num-1).select do |n|
      n % 3 == 0 || n %5 == 0
    end
    results.empty? ? 0 : results.inject(:+)
  end

  def initialize(*args)
    @args_array = []
    args.each do |arg|
      @args_array << arg
    end
  end

  def to(query_num)
    results = (1..query_num-1).select do |qn|
      num_check(qn)
    end
    results.empty? ? 0 : results.inject(:+)
  end

  def num_check(query_num)
    @args_array.each do |a|
      return true if query_num % a == 0
    end
    return false
  end

end
