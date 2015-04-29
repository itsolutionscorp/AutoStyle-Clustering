class Raindrops
  RAINDROPS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(n)
    reply = RAINDROPS.map { |prime, sound| sound if n % prime == 0 }.join
    reply.empty? ? n.to_s : reply
  end
end
