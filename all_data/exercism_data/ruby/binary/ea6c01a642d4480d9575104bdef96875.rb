class Binary
    def initialize(binary)
      @binary = binary.to_s.match(/[^10]/) ? 0 : binary
    end

    def to_decimal
      @binary.to_s.reverse.chars.each_with_index.map do |char, index|
        char == '1' ? 2**index : 0
      end.reduce(:+)
    end

end
