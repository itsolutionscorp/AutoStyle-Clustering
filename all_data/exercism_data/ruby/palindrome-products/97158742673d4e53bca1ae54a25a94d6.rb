class Palindromes
  def initialize(min_factor: 0, max_factor: 0)
    fail ArgumentError, 'Max must be > 0' if max_factor < 1
    fail ArgumentError, 'Max cannot be < Min' if max_factor < min_factor
    @min_factor = min_factor
    @max_factor = max_factor
  end

  Result = Struct.new :value, :factors

  def generate
    @palindromes = Hash.new { |h, k| h[k] = [] }
    
    factor_pairs do |i, j|
      @palindromes[i*j] << [i, j] if palindrome?(i * j)
    end
  end
  
  def largest
    Result.new(*@palindromes.max_by{ |k, _| k })
  end

  def smallest
    Result.new(*@palindromes.min_by{ |k, _| k })
  end
  
  private

  def factor_pairs
    (@min_factor..@max_factor).each do |i|
      (i..@max_factor).each do |j|
        yield i, j
      end
    end
  end
  
  def palindrome?(n)
    str_rep = n.to_s
    str_rep == str_rep.reverse
  end
end
