module Strain
  def keep
    keeping = []

    each_with_index do |item, index| 
      keeping << self[index] if yield(item) 
    end

    return keeping
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
