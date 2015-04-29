class Raindrops
  def self.convert(number)
    new(number).convert
  end

  def initialize(number)
    @number = number
  end

  def convert
    translation.empty? ? @number.to_s : translation
  end

  private

  def translation
    [3, 5, 7].map(&prime_translation).join
  end

  def prime_translation
    ->(prime) { dictionary[prime] if @number % prime == 0 }
  end

  def dictionary
    { 3 => "Pling", 5 => "Plang", 7 => "Plong" }
  end
end
