class Palindrome

  attr_reader :value, :factors

  def initialize(value, factors = [])
    @value   = value
    @factors = factors
  end

end

class Palindromes

  attr_reader :max_factor, :min_factor

  def initialize(max_factor: nil, min_factor: 1)
    @max_factor = max_factor
    @min_factor = min_factor
  end

  def generate
    # Set default value for any new item in the hash
    palindromes = Hash.new { |h, k| h[k] = Palindrome.new(k) }

    # Passes a code block to palindrome_builder
    # Create hash of palindromes using returned values
    palindrome_builder do |product, factors|
      palindromes[product].factors << factors
    end

    @ordered_palindromes = palindromes.values.sort_by(&:value)
  end

  def smallest
    @ordered_palindromes.first
  end

  def largest
    @ordered_palindromes.last
  end

  private

  def palindrome?(n)
    as_string = n.to_s
    as_string == as_string.reverse
  end

  def palindrome_builder(&block)
    # passes code block to enumerate_factors
    enumerate_factors do |a, b|
      product = a * b
      factor  = [a, b]

      # yield (product, factor) to block provided in generate
      # if product is a palindrome
      block.call(product, factor) if palindrome?(product)
    end
  end

  def enumerate_factors(&block)
    # loop through range forwards
    (min_factor..max_factor).each do |a|

      # loop through range backwards 
      (min_factor..a).each do |b|

        # if a is less than b, yield all (a, b) factors to palindrome builder
        a < b ? block.call(a, b) : block.call(b, a)
      end
    end
  end

end
