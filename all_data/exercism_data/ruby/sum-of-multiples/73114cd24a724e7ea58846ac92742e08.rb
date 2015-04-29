class SumOfMultiples
  @base_number = [3, 5]
  def initialize(*args)
    if args
      @base_number = []
      args.each { |arg| @base_number << arg }
      p @base_number
    end
    
  end
  def self.to(maximum)
    arrays_of_multiples = []
    p @base_number
    @base_number.each do |base_number|
      arrays_of_multiples << self.select_multiples(base_number, maximum)
    end
    arrays_union = self.unify_arrays(arrays_of_multiples)
    self.sum_elements_in(arrays_union)
  end
  def to(maximum)
    self.class.to(maximum)
  end
  
  def self.select_multiples(base_number, maximum)
    res = (0..maximum-1).to_a.select { |n| n % base_number == 0 }
    res
  end
  def self.unify_arrays(arrays_of_multiples)
    arrays_of_multiples.flatten
  end
  def self.sum_elements_in(array_union)
    array_union.reduce(&:+)
  end
end
