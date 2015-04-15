module Strain
  def keep
    self.inject([]) do |keepers, element|
      yield(element) ? keepers << element : keepers 
    end
  end

  def discard(&block)
    self - keep(&block)
  end
end

class Array
  include Strain
end
