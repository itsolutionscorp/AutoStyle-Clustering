module Strain
  def keep
    self.each_with_object(self.class.new) do |element, collection|
      collection << element if yield(element)
    end
  end

  def discard
    self.keep { |element| not yield(element) }
  end
end

class Array
  include Strain
end
