module Enumerable
  def accumulate
    return to_enum(:accumulate) unless block_given?

    reduce([]) do |new_array,item|
      new_array << yield(item)
    end
  end
end
