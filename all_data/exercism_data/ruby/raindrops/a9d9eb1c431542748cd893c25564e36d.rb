class Raindrops
  @rainhash = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(number)
    message = ''
    @rainhash.each do |prime, word|
      if number % prime == 0
        message << word
      end
    end
    if message.empty?
      message = number.to_s
    end
    message
  end
end
