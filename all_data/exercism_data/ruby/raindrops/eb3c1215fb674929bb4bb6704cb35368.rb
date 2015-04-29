class Raindrops

  TRANSLATIONS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  attr_reader :raindrop_count

  def self.convert(num)
    new(num).convert
  end

  def initialize(raindrop_count)
    @raindrop_count = raindrop_count
  end

  def convert
    translatable? ? translate_factors.join : raindrop_count.to_s
  end

  protected

  def prime_factors
    return [] if raindrop_count == 1
    [first_prime, *remaining_raindrops.prime_factors]
  end

  private

  def remaining_raindrops
    self.class.new(raindrop_count / first_prime)
  end

  def translatable?
    !translate_factors.empty?
  end

  def translate_factors
    prime_factors.uniq.map { |prime| TRANSLATIONS[prime] }.compact
  end

  def first_prime
    2.upto(raindrop_count).find { |x| divisible_by? x }
  end

  def divisible_by?(num)
    raindrop_count % num == 0
  end

end
