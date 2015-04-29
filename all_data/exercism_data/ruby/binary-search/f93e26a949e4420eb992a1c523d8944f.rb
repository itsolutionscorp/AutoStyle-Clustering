class BinarySearch
  attr_reader :list

  def initialize(list)
    raise ArgumentError unless list == list.sort

    @list = list
  end

  def search_for(key)
    raise RuntimeError if list.empty?

    case key <=> list[middle]
    when  0 then middle
    when -1 then self.class.new(list[0...middle]).search_for(key)
    when  1
      offset = middle + 1
      self.class.new(list[offset..-1]).search_for(key) + offset
    end
  end

  def middle
    list.size / 2
  end
end
