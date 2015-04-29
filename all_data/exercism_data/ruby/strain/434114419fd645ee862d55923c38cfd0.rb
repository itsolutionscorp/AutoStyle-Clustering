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
    to_a - keep(&predicate)
  end
end
