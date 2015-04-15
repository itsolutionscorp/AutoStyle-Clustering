class SumOfMultiples

  def initialize(*args)
    @args_array = args.length > 0 ? args : [3, 5]
  end

  def to(query_num)
    results = (1..query_num-1).select { |qn| num_check(qn) }
    results.empty? ? 0 : results.inject(:+)
  end

  def self.to(num)
    SumOfMultiples.new.to(num)
  end

  def num_check(query_num)
    @args_array.each do |a|
      return true if query_num % a == 0
    end
    return false
  end

end
