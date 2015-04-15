class Hamming
  class << self

    def compute(first, second)
      shorter = _shorter(first, second)
      longer = _longer(first, second)

      (0..shorter.length-1).count { |index| shorter[index] != longer[index] }
    end

    def _shorter(first, second)
      first.length < second.length ? first : second
    end

    def _longer(first, second)
      first.length < second.length ? second : first
    end
  end
end
