# Convert a binary string to a decimal value
# using first principles.  The binary string
# can have an optional leading minus sign and
# must contain at least one digit to the left of
# the decimal point.  The decimal point and 
# fractional part are optional.
# class Binary
#   def initialize(binary_string)
#     @binary_string = binary_string
#   end
  
#   def to_decimal
#     return 0 unless valid?
#     parse
#     calculate
#   end

#   private

#     def valid?
#       @binary_string.match /\A\-?[01]+[.]?[01]*\z/
#     end

#     def parse
#       @sign = @binary_string[0] == '-' ? -1 : 1
#       @binary_string.slice!(0) if @sign == -1
#       @integer_string, @fraction_string = @binary_string.split('.')
#     end

#     def calculate
#       @sign * (integer_values + fractional_values).inject(&:+)
#     end

#     def integer_values
#       return [] unless @integer_string
#       @integer_string.chars.reverse
#         .map.with_index{ |char, i| char.to_i * 2**i }
#     end

#     def fractional_values
#       return [] unless @fraction_string
#       @fraction_string.chars
#         .map.with_index{ |char, i| char.to_f / 2**(i+1) }
#     end
# end

class Binary

  def initialize(s)
    @s = s.tr('^01', '') == s ? s : "0"
  end

  def to_decimal
    @s.chars.inject(0) { |n, c| n += c.to_i + n }
  end

end
