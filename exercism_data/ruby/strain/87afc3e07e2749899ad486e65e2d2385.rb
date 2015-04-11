module Strainable
  def keep(&block)
    [].tap do |out|
      self.each { |e| out << e if block.call(e) }
    end
  end

  def discard(&block)
    [].tap do |out|
      self.each { |e| out << e unless block.call(e) }
    end
  end
end

class Array
  include Strainable
end
