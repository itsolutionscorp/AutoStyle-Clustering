class Raindrops

  # Map certain prime numbers to keywords they should output.
  PRIMEWORDS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong',
  }

  # Given a number, returns keyword string based on presence of prime factors.
  def self.convert number
    output = ''
    PRIMEWORDS.each do |prime, word|
      output = "#{output}#{word}" if number % prime == 0
    end
    return output === '' ? number.to_s : output
  end

end
