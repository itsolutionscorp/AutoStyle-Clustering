class Raindrops
  SOUNDS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(input)
    res = ''
    SOUNDS.each_pair { |num, word| res << word if input.modulo(num).zero? }
    res = input.to_s if res.empty?
    res
  end
end
