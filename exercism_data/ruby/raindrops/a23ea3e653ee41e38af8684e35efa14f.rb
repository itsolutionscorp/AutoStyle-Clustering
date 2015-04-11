class Raindrops
  def self.convert(n)
    rainspeak = rainspeak_from n

    return n.to_s if rainspeak == ''
    return rainspeak
  end

  private
  def self.rainspeak_from(number)
    rainspeak = ''
    rainspeak << 'Pling' if number % 3 == 0
    rainspeak << 'Plang' if number % 5 == 0
    rainspeak << 'Plong' if number % 7 == 0
    rainspeak
  end
end
