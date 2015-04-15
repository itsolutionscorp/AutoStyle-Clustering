module Enumerable
  def keep
    [].tap do |acc|
      each { |el| acc << el if yield(el) }
    end
  end

  def discard
    keep { |el| !yield(el) }
  end
end
