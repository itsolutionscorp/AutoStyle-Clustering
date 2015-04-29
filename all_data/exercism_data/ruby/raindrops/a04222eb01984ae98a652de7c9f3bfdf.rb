require 'prime' # Cheating...?

class Raindrops
  @chars = 'ioa'

  def self.convert(number)
    return number.to_s if (number.prime_division.flatten.uniq.sort & [3, 5, 7]).empty?
    (number.prime_division.flatten.uniq.sort & [3, 5, 7])
      .inject([]) { |a, e| a << "Pl#{@chars[e % 3]}ng" }.join
  end
end
