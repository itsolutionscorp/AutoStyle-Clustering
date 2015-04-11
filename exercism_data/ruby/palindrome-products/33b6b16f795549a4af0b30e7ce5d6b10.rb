class Palindrome
  attr_reader :value
  attr_accessor :factors

  def initialize(value)
    raise(ArgumentError, "This isn't a palindrome") unless Palindrome.check? value

    @value = value
    @factors = []
  end

  def self.check?(value)
    ds = digits(value)
    len = ds.length
    ds.each_with_index do |digit, i|
      return false unless digit == ds[len - i - 1]
    end
    return true
  end

  def <=>(other)
    value <=> other.value
  end

  private
  def self.digits(value)
    ds = []
    while value > 0
      ds << value % 10
      value = value / 10
    end
    ds
  end
end

class Palindromes
  def initialize(max_factor: 999, min_factor: 1)
    @max = max_factor
    @min = min_factor
  end

  def generate
    @list ||= (@min..@max).each_with_object([]) { |m, l|
      (m..@max).each_with_object(l) do |n, list|
        if Palindrome.check?( p = m * n)
          palindrome = list.find { |pal| pal.value == p }
          if palindrome.nil?
            palindrome = Palindrome.new(p)
            list << palindrome
          end 
          palindrome.factors << [m, n]
        end
      end
    }.sort
  end

  def largest
    generate.last
  end

  def smallest
    generate.first
  end
end
