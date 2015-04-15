class Raindrops
  RAINDROP_SPEAK = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def convert(number)
    out = ''
    RAINDROP_SPEAK.each do |prime, word|
      out << word if number % prime == 0
    end
    out = number.to_s if out.empty?

    out
  end
end
