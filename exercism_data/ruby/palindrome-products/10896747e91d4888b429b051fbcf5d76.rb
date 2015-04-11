class Palindromes
  def initialize(min_factor: 0, max_factor: 0)
    fail ArgumentError, 'Max must be > 0' if max_factor < 1
    fail ArgumentError, 'Max cannot be < Min' if max_factor < min_factor
    @min_factor = min_factor
    @max_factor = max_factor
  end

  Result = Struct.new :value, :factors

  attr_reader :smallest, :largest

  def generate
    palindromes = compute_palindromes

    min = palindromes.keys.min
    @smallest = Result.new(min, palindromes[min])

    max = palindromes.keys.max
    @largest = Result.new(max, palindromes[max])
  end
  
  private

  def compute_palindromes
    palindromes = Hash.new([])

    (@min_factor..@max_factor).each do |i|
      (i..@max_factor).each do |j|
        n = i * j
        next unless palindrome?(n)
        palindromes[n] += [[i, j]]
      end
    end
    palindromes
  end

  def palindrome?(n)
    str_rep = n.to_s
    str_rep == str_rep.reverse
  end
end
