class String
  def as_nr_string
    NR::String.new(to_s)
  end
end

module NR
  class Integer < ::Integer
    class << self
      def from_base_for_number(base, number)
        number.to_a.reverse.each_with_index.reduce(0) do |total, (digit, index)|
          total += digit.to_i * base**index
        end
      end

      def method_missing(name, args)
        if(name =~ /from_base_\d+/)
          base = name.to_s.match(/from_base_(\d+)/)[1].to_i
          from_base_for_number(base, args)
        end
      end
    end
  end
  class String < ::String
    def to_a
      chars.to_a
    end
  end
end

class Trinary
  attr_reader :base_ten_number
  def initialize(number_string)
    @base_ten_number = NR::Integer.from_base_3(number_string.as_nr_string)
  end

  def to_decimal
    base_ten_number
  end
end
