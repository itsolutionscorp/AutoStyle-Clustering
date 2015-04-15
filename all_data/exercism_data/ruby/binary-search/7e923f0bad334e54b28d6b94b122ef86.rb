class BinarySearch

  attr_reader :list

  def initialize(args)
    @list = args
    raise ArgumentError, "Sorted list required" unless sorted?
  end

  def sorted?
    list.sort == list
  end

  def search_for(item)
    from_index = 0
    to_index = list.length-1

    while from_index <= to_index
      midpoint = middle(from_index, to_index)
      if to_index == from_index &&  list[midpoint] != item
        raise RuntimeError, "Data not in list"
      end
      
      case list[midpoint]  <=> item   
      when 1 then to_index = midpoint - 1
      when 0 then return midpoint
      when -1 then from_index = midpoint + 1
      end
    end
  end

  def middle(from_index = 0, to_index = list.length)
    from_index + (to_index-from_index)/2
  end

end
