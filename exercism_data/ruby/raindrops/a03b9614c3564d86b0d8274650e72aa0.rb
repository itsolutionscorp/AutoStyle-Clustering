class Raindrops
  def self.convert(n)
    if (n % 3 == 0)
      puts 'Pling'
    elsif n % 5 == 0
      puts 'Plang'
    elsif n % 7 == 0
      puts 'Plong' 
    else
      puts n.to_s
    end
  end
end
