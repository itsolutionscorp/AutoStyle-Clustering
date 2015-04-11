class Raindrops

  Sounds = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert(num)
    results = self.make_sounds(num)

    results.empty? ? num.to_s : results
  end

  def self.make_sounds(num)
    matches = Sounds.select do |divider|
      num % divider == 0
    end

    matches.values.join('')
  end

end
