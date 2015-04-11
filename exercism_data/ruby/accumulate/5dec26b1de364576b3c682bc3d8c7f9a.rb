module Accumulateable
  def accumulate
    map = []

    if block_given? && length > 0
      each { |item| map << yield(item) }
    end

    map
  end
end

class Array
  include Accumulateable
end
