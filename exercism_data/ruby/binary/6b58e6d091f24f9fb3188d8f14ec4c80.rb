class Binary
  attr_reader :binary

  def initialize(value)
    @binary = [0]
    @binary = value.scan(/\d/).map(&:to_i) unless value =~ /[^01]/
  end

  def to_decimal()
    binary.reverse.each_with_index.inject(0) do |result, (n, i)|
      result += n * 2**i
    end
  end
end

# If we could just use the sweetness of Ruby...
#
# Class Binary
#   attr_reader :binary
#
#   def initialize(value)
#     @binary = value
#   end
#
#   def to_decimal
#     @binary.to_i(2)
#   end
# end
