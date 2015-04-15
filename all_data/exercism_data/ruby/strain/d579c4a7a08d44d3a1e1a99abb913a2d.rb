module Strain
  def keep
    map {|item| item if yield item}.compact
  end

  def discard
    map {|item| item unless yield item}.compact
  end
end

class Array
  include Strain
end
