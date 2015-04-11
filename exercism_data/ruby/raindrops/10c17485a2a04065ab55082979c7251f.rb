require 'prime'

class Raindrops
  attr_accessor :factors

  # Converts a number into a string, depending on the number's prime factors.
  # @return [String] the converted string, based off the README description
  def self.convert(number)
    answer = ''
    @factors = factorize(number)
    if factors_includes?([3,5,7], :some)
      answer << 'Pling' if factors_includes?(3)
      answer << 'Plang' if factors_includes?(5)
      answer << 'Plong' if factors_includes?(7)
    elsif !factors_includes?([3,5,7])
      answer << number.to_s
    end

    return answer
  end

  private

  # Checks if the defined factor includes a number or set of numbers.
  #
  # @param [Array or Fixnum] n the number or set of numbers to compare to
  # @param [Symbol] group if :all, all must match.  If :some, one or more
  #   must match.  Only has an effect if n is an Array.
  #
  # @return [Boolean] If n is an Array, returns true if {#factors} includes group
  #   values of n.  If n is a Fixnum, returns true if {#factors} includes n.
  #   Else false.
  def self.factors_includes?(n, group = :all)
    if n.is_a? Array
      begin
        result = n.map { |x| @factors.include?(x) }.uniq
        if group == :all
          result.include?(false) ? false : true
        elsif group == :some
          result.include?(true)
        else
          raise "Invalid Argument #{group}: expected :all or :some."
        end
      rescue RuntimeError => e
        puts e
      end
    else
      return @factors.include?(n)
    end
  end

  # Finds all prime factors of number.
  # @param [Fixnum] number number to factor
  # @return an empty array if number is 1 or has no prime factors, else an array of number's prime
  #   factors.
  def self.factorize(number)
    return [] if number == 1
    answer = 2.upto(number).to_a
    answer.keep_if { |n| n.prime? }
    answer.map! { |n| number.fdiv(n) == number.fdiv(n).floor ? n : nil }
    answer.compact!
  end
end
