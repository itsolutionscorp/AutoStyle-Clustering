class BinarySearch

  attr_reader :list 

  def initialize(list)
    @list = list.clone
    validate 
  end

  def search_for(item)
    raise RuntimeError if list.empty?
    if list[middle] > item
      self.class.new(list[0, middle]).search_for(item)
    elsif list[middle] < item
      middle + 1 + self.class.new(list[(middle + 1)..-1]).search_for(item)
    else
      middle
    end
  end

  def middle
    list.length / 2
  end

private

  def validate
    raise ArgumentError if list.each_cons(2).any? { |a, b| a >= b }
  end

end
