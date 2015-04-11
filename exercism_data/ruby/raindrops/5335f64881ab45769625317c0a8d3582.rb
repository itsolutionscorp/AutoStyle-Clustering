class Raindrops
  VOCAB = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert num
    output = ''
    VOCAB.keys.each { |v| output += VOCAB[v] if num.remainder(v).zero? }
    output.empty? ? num.to_s : output
  end
end
