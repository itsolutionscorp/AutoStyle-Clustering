module Strain
  def keep
    self.inject([]) do |extract, number|
      yield(number) ? extract << number : extract 
    end
  end

  def discard
    self.inject([]) do |extract, number|
      !yield(number) ? extract << number : extract
    end
  end
end

class Array
  include Strain
end
