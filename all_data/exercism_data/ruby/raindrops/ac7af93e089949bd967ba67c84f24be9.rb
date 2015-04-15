require 'prime'
class Raindrops < Struct.new(:number)

  SOUNDS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(number)
    new(number).output
  end

  def output
    sound || number.to_s
  end

  private

  def factorization
    Prime.prime_division number
  end

  def primes
    factorization.map &:first
  end

  def primes_with_sound
    primes & SOUNDS.keys
  end

  def prime_sounds
    primes_with_sound.map &SOUNDS.method(:[])
  end

  def sound
    return if prime_sounds.empty?
    prime_sounds.join
  end


end
