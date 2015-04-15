class Trinary
    def initialize(trinary)
      @trinary = trinary.to_s.match(/[^210]/) ? 0 : trinary
    end

    def to_decimal
      @trinary.to_s.reverse.chars.each_with_index.map do |char, index|
        char == 0 ? 0 : (3**index) * char.to_i
      end.reduce(:+)
    end

end
