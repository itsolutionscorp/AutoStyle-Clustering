class Raindrops
  @@prime_sounds = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

  def self.convert(num)
    sounds = String.new

    @@prime_sounds.each do |k,v|
      sounds += v if (num % k).eql?(0)
    end

    sounds.empty? ? num.to_s : sounds
  end
end
