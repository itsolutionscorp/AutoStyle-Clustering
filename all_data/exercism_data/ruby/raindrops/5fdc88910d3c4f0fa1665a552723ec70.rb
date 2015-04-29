class Raindrops
  CONVERSIONS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(n)
    final = n.to_s
    CONVERSIONS.each_key do |key|
      if n % key == 0
        final << CONVERSIONS[key]
      end
    end
    final == n.to_s ? final : final.delete(n.to_s)
  end
end
