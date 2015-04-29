module Raindrops
  DROPSPEAK = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(number)
    dropstring = DROPSPEAK.keys.reduce('') do |str, prime|
      str += DROPSPEAK[prime] if number % prime == 0
      str
    end
    return number.to_s if dropstring.empty?
    dropstring
  end
end
