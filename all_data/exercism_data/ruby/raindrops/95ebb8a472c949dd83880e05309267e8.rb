module PlingPlangPlong
  refine Fixnum do
    def prime?
      raise TypeError, 'Give me a number!' unless self.is_a? Fixnum
      return false if [nil, 1,2].include?(self)

      (3..self).step(2).map do |n|
        return false if (self % n).zero? && self != n
        true
      end.any?
    end

    [:pling, :plang, :plong].each do |pl_ng|
      define_method("#{pl_ng}able?") do
        raise TypeError, 'Give me a plingable number!' unless self.is_a? Fixnum
        case pl_ng
          when :pling then self % 3 == 0
          when :plang then self % 5 == 0
          when :plong then self % 7 == 0
          else super pl_ng
        end
      end
    end
  end
end

class Raindrops
  using PlingPlangPlong

  def self.convert(input)
    process_input(input)
  end

  private

  @primes ||= (3..13_000).select { |n| n.prime? }

  def self.process_input(input)
    return "1" if input == 1
    prime_list = prime_factors(input)

    results = []
    prime_list.map do |x|
      results << "Pling" if x.plingable?
      results << "Plang" if x.plangable?
      results << "Plong" if x.plongable?
      results << input if results.length.zero?
    end
    results.join
  end

  def self.prime_factors(integer)
    if integer > @primes.last
      (@primes.last..integer).collect { |n| @primes.push(n) if prime_cachable?(n) }
    end
    @primes.collect { |n| n if (integer % n).zero? }.compact
  end

  def self.prime_cachable?(number)
    @primes.include(number) || number.prime?
  end
end
