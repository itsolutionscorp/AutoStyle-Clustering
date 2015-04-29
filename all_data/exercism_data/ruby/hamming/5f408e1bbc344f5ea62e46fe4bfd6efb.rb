class Hamming
  class << self
    def compute(s, t)
      s.chars.each_with_index.inject(0) do |memo, (element, index)|
        memo += score(element, t.slice(index))
      end
    end

    private

    def score(s, t)
      return 0 if s == t
      1
    end
  end
end
