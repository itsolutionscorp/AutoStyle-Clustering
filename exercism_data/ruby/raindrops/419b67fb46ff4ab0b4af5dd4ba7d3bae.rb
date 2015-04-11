class Raindrops
  def convert(num)
    raindrops = ''
    { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }.each do |prime, drop|
      raindrops += drop if num.modulo(prime).zero?
    end

    raindrops.empty? ? num.to_s : raindrops
  end
end
