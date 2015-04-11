class SumOfMultiples
  def initialize(*args)
    @base_number = []
    args.each { |arg| @base_number << arg }
    
  end
  def to(maximum)
    arrays_of_multiples = []
    @base_number.each do |base_number|
      arrays_of_multiples << select_multiples(base_number, maximum).to_set
    end
    arrays_union = unify_arrays(arrays_of_multiples)
    sum_elements_in(arrays_union)
  end
  def self.to(maximum)
    new(3,5).to(maximum)
  end
  
  def select_multiples(base_number, maximum)
    res = (0..maximum-1).to_a.select { |n| n % base_number == 0 }
    res
  end
  def unify_arrays(arrays_of_multiples)
    arrays_of_multiples.reduce { |res, ary_of_mult| res.union(ary_of_mult) }
  end
  def sum_elements_in(array_union)
    array_union.reduce(&:+)
  end
end
