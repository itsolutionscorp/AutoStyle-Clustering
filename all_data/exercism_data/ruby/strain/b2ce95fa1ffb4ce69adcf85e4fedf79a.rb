module Strain
  def keep
    self.inject([]) do |extracts, element|
      yield(element) ? extracts << element : extracts 
    end
  end

  def discard
    self.inject([]) do |extracts, element|
      !yield(element) ? extracts << element : extracts
    end
  end
end

class Array
  include Strain
end
