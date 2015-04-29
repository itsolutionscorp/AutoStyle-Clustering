class Raindrops

  CONVERSION = { 1 => '1', 3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert(number)
    translation = factors(number).map { |factor| CONVERSION[factor] }.uniq.join
    translation.empty? ? number.to_s : translation
  end

  private

  def self.factors(n)
    return [] if n == 1
    factor = (2..n).detect {|x| n % x == 0}
    [factor] + factors(n / factor)
  end
end
