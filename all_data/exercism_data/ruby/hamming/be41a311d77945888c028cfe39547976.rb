class Hamming
  class << self

    def compute(first, second)
      (0..[first.length, second.length].min-1).count { |i| first[i] != second[i] }
    end
  end
end
