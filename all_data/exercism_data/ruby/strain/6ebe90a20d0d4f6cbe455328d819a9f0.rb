# added 'using Strain' to test file to import this refinement

module Strain
  refine Array do
    def keep(&block)
      self.each_with_object([]) do |element, result|
        result << element if yield element
      end
    end

    def discard(&block)
      self.each_with_object([]) do |element, result|
        result << element unless yield element
      end
    end
  end
end
