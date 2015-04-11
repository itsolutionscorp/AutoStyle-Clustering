class Raindrops
  def self.convert(x)
    {3=>'Pling',5=>'Plang',7=>'Plong'}.inject(x.to_s){|s,(y,p)|x%y==0?s.sub(/\d*(.*)/,"\\1#{p}"):s}
  end
end
