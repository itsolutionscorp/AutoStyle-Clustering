class Raindrops < Struct.new(:number)
  DROPFACTORS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }.freeze

  def self.convert(number)
    new(number).convert
  end

  def convert
    matching_drops.join.tap do |response|
      response << number.to_s if response.empty?
    end
  end

private

  def matching_drops
    DROPFACTORS.select { |factor, _| prime_factor? factor }.values
  end

  def prime_factor?(factor)
    number % factor == 0
  end
end
