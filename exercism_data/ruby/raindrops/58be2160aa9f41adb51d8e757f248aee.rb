class Raindrops
  def self.convert(n)
    rainspeak = ''
    rainspeak << 'Pling' if n % 3 == 0
    rainspeak << 'Plang' if n % 5 == 0
    rainspeak << 'Plong' if n % 7 == 0

    return n.to_s if rainspeak == ''
    rainspeak
  end
end
