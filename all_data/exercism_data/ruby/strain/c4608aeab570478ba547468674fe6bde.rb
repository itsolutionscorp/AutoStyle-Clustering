module Strain
  def keep
    return self if self.empty?
    self.each_with_object([]) { |element, keeper| keeper << element if yield(element) }
  end

  def discard
    return self if self.empty?
    self.each_with_object([]) { |element, remains| remains << element unless yield(element) }
  end
end

class Array
  include Strain
end
