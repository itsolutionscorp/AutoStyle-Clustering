class BinarySearch
  attr_reader :list

  def initialize(list, offset = 0)
    @list = list.sort
    @offset = offset
    raise ArgumentError, "List must be pre-sorted" unless @list == list
  end

  def search_for(sought_value)
    if list.empty?
      raise "No such value in list!"
    elsif middle_value == sought_value
      middle + offset
    elsif middle_value > sought_value
      self.class.new(list_before_middle, offset).search_for(sought_value)
    elsif middle_value < sought_value
      self.class.new(list_after_middle, middle + 1).search_for(sought_value)
    end
  end

  def middle
    list.length / 2
  end

  private

  attr_reader :offset

  def list_before_middle
    list[0...middle]
  end

  def middle_value
    list[middle]
  end

  def list_after_middle
    list[(middle + 1)..-1]
  end
end
