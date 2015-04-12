module Hamming
  class << self
    def compute(left, right)
      pairs = left.chars.zip right.chars
      pairs.count { |l, r| l != r }
    end
  end
end
