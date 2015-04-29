class BinarySearch

  attr_reader :list

  def initialize(list)
    raise ArgumentError unless sorted? list
    @list = list.clone
  end

  def search_for(item)
    raise RuntimeError if list.empty?
    find_on_left(item) || find_on_right(item) || middle
  end

  def middle
    @middle ||= list.length / 2
  end

private

  def sorted?(list)
    list.each_cons(2).all? { |a, b| a < b }
  end

  def find_on_left(item)
    search(item, on_left) if middle_element > item
  end

  def find_on_right(item)
    search(item, on_right) + middle + 1 if middle_element < item
  end

  def search(item, list)
    self.class.new(list).search_for(item)
  end

  def middle_element
    list[middle]
  end

  def on_left
    list[0, middle]
  end

  def on_right
    list[(middle + 1)..-1]
  end

end
