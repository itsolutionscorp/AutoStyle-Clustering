class BinarySearch
  attr_reader :list

  def initialize(list, offset=0)
    raise ArgumentError if list != list.sort
    @list = list 
    @offset = offset
  end

  def search_for(item)
    raise RuntimeError if @list.empty?

    if @list[middle] == item
      return @offset + middle
    elsif @list[middle] < item
      @offset += middle + 1
      self.class.new(list_after_middle, @offset).search_for(item)
    else
      self.class.new(list_before_middle, @offset).search_for(item)
    end
  end

  def middle
    @list.size / 2
  end

  private

  def list_before_middle
    @list[0..middle-1]
  end

  def list_after_middle
    @list[(middle + 1)..-1]
  end
end
