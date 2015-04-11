class Raindrops
  def convert(num)
    prime_drops = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
    raindrops = prime_drops.each_with_object('') do |(prime, drop), s|
      s << drop if num.modulo(prime).zero?
    end

    raindrops.empty? ? num.to_s : raindrops
  end
end
