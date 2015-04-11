class Binary

  attr_reader :binary

  def initialize binary
    @binary = binary
  end

  def to_decimal 
    return 0 if !!(binary =~ /[a-zA-Z]/)
    binary_array = binary.chars.map(&:to_i)

    binary_array.each.with_index.inject(0) do |sum,(n,i)|
      sum += 2 ** (binary_array.length-i-1) * n
    end
  end

end
