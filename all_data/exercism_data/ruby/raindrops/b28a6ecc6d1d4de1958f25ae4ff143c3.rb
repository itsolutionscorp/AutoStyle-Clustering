class Raindrops
  def self.convert(num)
    complements = {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
    }
    words = []
    complements.keys.map do |key_num|
      if num % key_num == 0
        words << complements.fetch(key_num)
      end
    end

    if words.compact.length > 0
      words.join
    else
      num.to_s
    end
  end
end
