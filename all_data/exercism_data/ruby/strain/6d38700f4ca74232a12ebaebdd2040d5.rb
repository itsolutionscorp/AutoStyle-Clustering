# Generic solution for enumerables
module Enumerable
  def keep(&predicate)
    result = []
    each do |item|
      result << item if yield item
    end
    result
  end

  def discard(&predicate)
    result = []
    each do |item|
      result << item unless yield item
    end
    result
  end
end
