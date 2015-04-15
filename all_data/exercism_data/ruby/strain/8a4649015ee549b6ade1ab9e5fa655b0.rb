module Strain
  def keep(&block)
    Array.new.tap do |result|
      self.each do |i|
        result.push(i) if block.call(i)
      end
    end
  end

  def discard(&block)
    self - keep(&block)
  end
end

Array.prepend Strain
