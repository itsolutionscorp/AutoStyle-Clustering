class Hamming

  class << self
    def compute(a, b)
      local_a, local_b = normalize_pair a, b
      [].tap do |hammer|
        local_a.each_with_index do |value, index|
          hammer << value if local_b[index] != value
        end
      end.size
    end

  private
    def normalize_pair a, b
      length = [a.size, b.size].min
      [normalize(a, length), normalize(b, length)]
    end

    def normalize string, length
      string.split('')[0...length]
    end

  end
end
