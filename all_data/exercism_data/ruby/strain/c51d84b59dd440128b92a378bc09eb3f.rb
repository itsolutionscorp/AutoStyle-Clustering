module Strain
  def keep
    map do |item| 
      item if yield item
    end.compact
  end

  def discard
    discarding = []

    each_with_index do |item, index| 
      discarding << self[index] unless yield(item) 
    end

    return discarding
  end
end

class Array
  include Strain
end
