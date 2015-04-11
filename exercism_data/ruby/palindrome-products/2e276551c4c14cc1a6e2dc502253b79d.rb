class Palindromes
  attr_reader :factors, :largest, :smallest, :palindromes

  def initialize(options)
    min_factor = options.fetch(:min_factor,  1)
    max_factor = options.fetch(:max_factor, 10)
    @factors = (min_factor..max_factor).to_a
    @largest = @smallest = nil
    @palindromes = {}
  end

  def generate
    potential_factors = factors.product(factors).map(&:sort).uniq
    potential_factors.each do |current_factors|
      result = current_factors.reduce(:*).to_s
      next if result != result.reverse

      insert_factors(result.to_i, current_factors)
    end
    temp = palindromes.sort
    @smallest = Palindrome.new(temp.first)
    @largest = Palindrome.new(temp.last)
  end

  def insert_factors(palindrome, factors)
    (palindromes[palindrome] ||= []) << factors
  end
end

class Palindrome
  attr_reader :value, :factors

  def initialize(info)
    @value = info[0]
    @factors = info[1]
  end
end
