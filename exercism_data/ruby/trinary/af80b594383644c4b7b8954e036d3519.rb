class Trinary
    def initialize(trinary)
      @trinary = trinary.to_s.match(/[^210]/) ? 0 : trinary
    end

    def to_decimal
      @trinary.to_s.reverse.chars.each_with_index.map do |char, index|
        case char
        when 0 
          0
        else
          (3**index) * char.to_i
        end
      end.reduce(:+)
    end

end
