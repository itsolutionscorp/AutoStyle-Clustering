class Sieve < Struct.new(:limit)
  def primes
    marked = []
    range  = (2..limit).to_a

    range.each do |n|
      next if marked.include?(n)
      marked += range.select { |v| v % n == 0 && v != n }
    end

    range - marked
  end
end
